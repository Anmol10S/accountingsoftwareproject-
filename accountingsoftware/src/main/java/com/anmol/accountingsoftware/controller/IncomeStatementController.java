package com.anmol.accountingsoftware.controller;

import com.anmol.accountingsoftware.DTO.IncomeStatementResponseDTO;
import com.anmol.accountingsoftware.service.IncomeStatementService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/income-statement")
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeStatementController {

    private final IncomeStatementService incomeStatementService;

    public IncomeStatementController(

            IncomeStatementService incomeStatementService){

        this.incomeStatementService = incomeStatementService;

    }

    @GetMapping

    public IncomeStatementResponseDTO getIncomeStatement(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to){

        return incomeStatementService.getIncomeStatement(

                from,

                to

        );

    }

}