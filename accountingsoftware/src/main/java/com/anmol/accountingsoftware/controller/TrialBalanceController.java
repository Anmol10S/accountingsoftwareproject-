package com.anmol.accountingsoftware.controller;

import com.anmol.accountingsoftware.DTO.TrialBalanceResponseDTO;
import com.anmol.accountingsoftware.service.TrialBalanceService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/trial-balance")
@CrossOrigin(origins = "http://localhost:4200")
public class TrialBalanceController {

    private final TrialBalanceService trialBalanceService;

    public TrialBalanceController(
            TrialBalanceService trialBalanceService) {

        this.trialBalanceService = trialBalanceService;

    }

    @GetMapping

    public TrialBalanceResponseDTO getTrialBalance(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return trialBalanceService.getTrialBalance(from, to);

    }

}