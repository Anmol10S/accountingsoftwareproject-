package com.anmol.accountingsoftware.controller;

import com.anmol.accountingsoftware.DTO.LedgerResponseDTO;
import com.anmol.accountingsoftware.service.LedgerService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/ledger")
@CrossOrigin(origins = "http://localhost:4200")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {

        this.ledgerService = ledgerService;

    }

    @GetMapping

    public LedgerResponseDTO getLedger(

            @RequestParam Long accountId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return ledgerService.getLedger(accountId, from, to);

    }

}