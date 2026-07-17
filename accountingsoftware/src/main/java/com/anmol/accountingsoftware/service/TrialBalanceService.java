package com.anmol.accountingsoftware.service;

import com.anmol.accountingsoftware.DTO.TrialBalanceEntryResponseDTO;
import com.anmol.accountingsoftware.DTO.TrialBalanceResponseDTO;
import com.anmol.accountingsoftware.entity.JournalLineEntity;
import com.anmol.accountingsoftware.repository.JournalLineRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrialBalanceService {

    private final JournalLineRepository journalLineRepository;

    public TrialBalanceService(
            JournalLineRepository journalLineRepository) {

        this.journalLineRepository = journalLineRepository;

    }

    public TrialBalanceResponseDTO getTrialBalance(

            LocalDate fromDate,

            LocalDate toDate) {

        List<JournalLineEntity> journalLines =
                journalLineRepository
                        .getTrialBalanceLines(
                                fromDate,
                                toDate);

        Map<Long, Double> balances =
                new LinkedHashMap<>();

        Map<Long, TrialBalanceEntryResponseDTO> accountMap =
                new LinkedHashMap<>();

        for (JournalLineEntity line : journalLines) {

            Long accountId = line.getAccount().getId();

            balances.putIfAbsent(accountId, 0.0);

            // Trial Balance uses raw debit - credit balance
            balances.put(

                    accountId,

                    balances.get(accountId)
                            + line.getDebit()
                            - line.getCredit()

            );

            accountMap.putIfAbsent(

                    accountId,

                    createEntry(line)

            );

        }

        List<TrialBalanceEntryResponseDTO> entries =
                new ArrayList<>();

        double totalDebit = 0;

        double totalCredit = 0;

        for (Long accountId : accountMap.keySet()) {

            TrialBalanceEntryResponseDTO dto =
                    accountMap.get(accountId);

            double balance =
                    balances.get(accountId);

            if (balance >= 0) {

                dto.setDebit(balance);

                dto.setCredit(0.0);

                totalDebit += balance;

            }

            else {

                dto.setDebit(0.0);

                dto.setCredit(Math.abs(balance));

                totalCredit += Math.abs(balance);

            }

            entries.add(dto);

        }

        TrialBalanceResponseDTO response =
                new TrialBalanceResponseDTO();

        response.setFromDate(fromDate);

        response.setToDate(toDate);

        response.setEntries(entries);

        response.setTotalDebit(totalDebit);

        response.setTotalCredit(totalCredit);

        response.setBalanced(

                Math.abs(totalDebit - totalCredit) < 0.001

        );

        return response;

    }

    private TrialBalanceEntryResponseDTO createEntry(

            JournalLineEntity line) {

        TrialBalanceEntryResponseDTO dto =
                new TrialBalanceEntryResponseDTO();

        dto.setAccountId(
                line.getAccount().getId());

        dto.setAccountCode(
                line.getAccount().getCode());

        dto.setAccountName(
                line.getAccount().getName());

        return dto;

    }

}