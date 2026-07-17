package com.anmol.accountingsoftware.controller;

import com.anmol.accountingsoftware.DTO.BalanceSheetResponseDTO;
import com.anmol.accountingsoftware.service.BalanceSheetService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/balance-sheet")
@CrossOrigin(origins = "http://localhost:4200")
public class BalanceSheetController {

    private final BalanceSheetService balanceSheetService;

    public BalanceSheetController(
            BalanceSheetService balanceSheetService) {

        this.balanceSheetService = balanceSheetService;

    }

    @GetMapping

    public BalanceSheetResponseDTO getBalanceSheet(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return balanceSheetService.getBalanceSheet(

                from,

                to

        );

    }

}