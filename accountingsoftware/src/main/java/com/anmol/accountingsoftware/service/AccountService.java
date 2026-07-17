package com.anmol.accountingsoftware.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anmol.accountingsoftware.DTO.AccountDTO;
import com.anmol.accountingsoftware.DTO.AccountResponseDTO;
import com.anmol.accountingsoftware.entity.AccountEntity;
import com.anmol.accountingsoftware.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // Constructor Injection

    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    // ================= CREATE ACCOUNT =================

    public AccountResponseDTO createAccount(
            AccountDTO request) {

        if (accountRepository.existsByCode(
                request.getCode())) {

            throw new RuntimeException(
                    "Account code already exists");
        }

        if (accountRepository.existsByName(
                request.getName())) {

            throw new RuntimeException(
                    "Account name already exists");
        }

        AccountEntity account = new AccountEntity();

        account.setCode(request.getCode());

        account.setName(request.getName());

        account.setType(request.getType());

        AccountEntity savedAccount =
                accountRepository.save(account);

        return convertToResponse(savedAccount);
    }


    // ================= GET ACTIVE ACCOUNTS =================

    public List<AccountResponseDTO>
            getAllAccounts() {

        return accountRepository.findByActiveTrue()

                .stream()

                .map(this::convertToResponse)

                .collect(Collectors.toList());
    }


    // ================= GET ALL ACCOUNTS =================

    public List<AccountResponseDTO>
            getAllAccountsIncludingInactive() {

        return accountRepository.findAll()

                .stream()

                .map(this::convertToResponse)

                .collect(Collectors.toList());
    }


    // ================= GET ACCOUNT BY ID =================

    public AccountResponseDTO getAccountById(
            Long id) {

        AccountEntity account =
                accountRepository.findById(id)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Account not found"));

        return convertToResponse(account);
    }


    // ================= UPDATE ACCOUNT =================

    public AccountResponseDTO updateAccount(

            Long id,

            AccountDTO request) {

        AccountEntity account =
                accountRepository.findById(id)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Account not found"));


        account.setCode(request.getCode());

        account.setName(request.getName());

        account.setType(request.getType());

        AccountEntity updatedAccount =
                accountRepository.save(account);

        return convertToResponse(
                updatedAccount);
    }


    // ================= DEACTIVATE ACCOUNT =================

    public void deactivateAccount(Long id) {

        AccountEntity account =
                accountRepository.findById(id)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Account not found"));

        account.setActive(false);

        accountRepository.save(account);
    }


    // ================= ENTITY -> DTO =================

    private AccountResponseDTO convertToResponse(

            AccountEntity account) {

        AccountResponseDTO response =

                new AccountResponseDTO();


        response.setId(account.getId());

        response.setCode(account.getCode());

        response.setName(account.getName());

        response.setType(account.getType());

        response.setActive(account.isActive());

        response.setCreatedAt(
                account.getCreatedAt());

        response.setCreatedBy(
                account.getCreatedBy());

        response.setUpdatedAt(
                account.getUpdatedAt());

        response.setUpdatedBy(
                account.getUpdatedBy());


        return response;
    }
}