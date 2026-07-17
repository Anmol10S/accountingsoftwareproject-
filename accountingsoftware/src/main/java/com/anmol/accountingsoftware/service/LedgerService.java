package com.anmol.accountingsoftware.service;

import com.anmol.accountingsoftware.DTO.LedgerEntryResponseDTO;
import com.anmol.accountingsoftware.DTO.LedgerResponseDTO;
import com.anmol.accountingsoftware.entity.JournalLineEntity;
import com.anmol.accountingsoftware.repository.JournalLineRepository;
import com.anmol.accountingsoftware.service.accounting.AccountingEngine;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LedgerService {

    private final JournalLineRepository journalLineRepository;

    public LedgerService(
            JournalLineRepository journalLineRepository) {

        this.journalLineRepository = journalLineRepository;

    }

    public LedgerResponseDTO getLedger(

            Long accountId,

            LocalDate fromDate,

            LocalDate toDate) {

        List<JournalLineEntity> previousEntries =
                journalLineRepository
                        .findByAccountIdAndActiveTrueAndJournalEntryTransactionDateBefore(
                                accountId,
                                fromDate);

        double openingBalance = 0;

        for (JournalLineEntity line : previousEntries) {

            openingBalance =
        AccountingEngine.calculateBalance(

                line.getAccount().getType(),

                openingBalance,

                line.getDebit(),

                line.getCredit()

        );

        }

        List<JournalLineEntity> currentEntries =
                journalLineRepository
                        .findByAccountIdAndActiveTrueAndJournalEntryTransactionDateBetweenOrderByJournalEntryTransactionDateAsc(
                                accountId,
                                fromDate,
                                toDate);

        List<LedgerEntryResponseDTO> entries =
                new ArrayList<>();

        double runningBalance = openingBalance;

        for (JournalLineEntity line : currentEntries) {

            LedgerEntryResponseDTO dto =
                    new LedgerEntryResponseDTO();

            dto.setTransactionDate(
                    line.getJournalEntry().getTransactionDate());

            dto.setReferenceNo(
                    line.getJournalEntry().getReferenceNo());

            dto.setDescription(
                    line.getJournalEntry().getDescription());

            dto.setAccountName(
                    line.getAccount().getName());

            dto.setDebit(
                    line.getDebit());

            dto.setCredit(
                    line.getCredit());

            runningBalance =
                AccountingEngine.calculateBalance(

                        line.getAccount().getType(),

                        runningBalance,

                        line.getDebit(),

                        line.getCredit()

                );

            dto.setBalance(Math.abs(runningBalance));

            dto.setBalanceType(

                runningBalance >= 0

                ? "Dr"

                : "Cr"

            );

            entries.add(dto);

        }

        LedgerResponseDTO response =
                new LedgerResponseDTO();

        if (!currentEntries.isEmpty()) {

            response.setAccountName(
                    currentEntries.get(0)
                            .getAccount()
                            .getName());

        }

        response.setFromDate(fromDate);

        response.setToDate(toDate);

        response.setOpeningBalance(Math.abs(openingBalance));

        response.setOpeningBalanceType(

                openingBalance >= 0

                ? "Dr"

                : "Cr"

        );

        response.setClosingBalance(Math.abs(runningBalance));

        response.setClosingBalanceType(

                runningBalance >= 0

                ? "Dr"

                : "Cr"

        );

        response.setEntries(entries);

        return response;

    }

}