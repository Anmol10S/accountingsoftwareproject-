package com.anmol.accountingsoftware.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.anmol.accountingsoftware.DTO.AccountDTO;
import com.anmol.accountingsoftware.DTO.AccountResponseDTO;
import com.anmol.accountingsoftware.service.AccountService;
import org.springframework.http.ResponseEntity;

@RestController

@RequestMapping("/accounts")

public class AccountController {

    private final AccountService accountService;


    // Constructor Injection

    public AccountController(
            AccountService accountService) {

        this.accountService = accountService;
    }


    // ================= CREATE ACCOUNT =================

    @PostMapping

    public AccountResponseDTO createAccount(

            @RequestBody
            AccountDTO request) {

        return accountService.createAccount(
                request);
    }


    // ================= GET ACTIVE ACCOUNTS =================

    @GetMapping

    public List<AccountResponseDTO>
            getAllAccounts() {

        return accountService.getAllAccounts();
    }


    // ================= GET ALL ACCOUNTS =================

    @GetMapping("/all")

    public List<AccountResponseDTO>
            getAllAccountsIncludingInactive() {

        return accountService
                .getAllAccountsIncludingInactive();
    }


    // ================= GET ACCOUNT BY ID =================

    @GetMapping("/{id}")

    public AccountResponseDTO getAccountById(

            @PathVariable
            Long id) {

        return accountService.getAccountById(
                id);
    }


    // ================= UPDATE ACCOUNT =================

    @PutMapping("/{id}")

    public AccountResponseDTO updateAccount(

            @PathVariable
            Long id,

            @RequestBody
            AccountDTO request) {

        return accountService.updateAccount(
                id,
                request);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable Long id) {
    
        accountService.deactivateAccount(id);
    
        return ResponseEntity.noContent().build();
    }
}