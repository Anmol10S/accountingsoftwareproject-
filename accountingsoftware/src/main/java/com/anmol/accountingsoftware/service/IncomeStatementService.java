package com.anmol.accountingsoftware.service;

import com.anmol.accountingsoftware.DTO.IncomeStatementEntryResponseDTO;
import com.anmol.accountingsoftware.DTO.IncomeStatementResponseDTO;
import com.anmol.accountingsoftware.entity.AccountType;
import com.anmol.accountingsoftware.entity.JournalLineEntity;
import com.anmol.accountingsoftware.repository.JournalLineRepository;
import com.anmol.accountingsoftware.service.accounting.AccountingEngine;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class IncomeStatementService {

    private final JournalLineRepository journalLineRepository;

    public IncomeStatementService(
            JournalLineRepository journalLineRepository) {

        this.journalLineRepository = journalLineRepository;

    }

    public IncomeStatementResponseDTO getIncomeStatement(

            LocalDate fromDate,

            LocalDate toDate) {

        List<JournalLineEntity> journalLines =
                journalLineRepository
                        .getIncomeStatementLines(
                                fromDate,
                                toDate);

        Map<Long, Double> balances =
                new LinkedHashMap<>();

        Map<Long, IncomeStatementEntryResponseDTO> accountMap =
                new LinkedHashMap<>();

        Map<Long, AccountType> accountTypes =
                new HashMap<>();

        for (JournalLineEntity line : journalLines) {

            AccountType type =
                    line.getAccount().getType();

            if (type != AccountType.REVENUE &&
                    type != AccountType.EXPENSE) {

                continue;

            }

            Long accountId =
                    line.getAccount().getId();

            balances.putIfAbsent(accountId, 0.0);

            balances.put(

                    accountId,

                    AccountingEngine.calculateBalance(

                            line.getAccount().getType(),

                            balances.get(accountId),

                            line.getDebit(),

                            line.getCredit()

                    )
            );

            accountTypes.put(accountId, type);

            accountMap.putIfAbsent(

                    accountId,

                    createEntry(line)

            );

        }

        List<IncomeStatementEntryResponseDTO> revenues =
                new ArrayList<>();

        List<IncomeStatementEntryResponseDTO> expenses =
                new ArrayList<>();

        double totalRevenue = 0;

        double totalExpense = 0;

        for (Long accountId : accountMap.keySet()) {

            IncomeStatementEntryResponseDTO dto =
                    accountMap.get(accountId);

            double amount =balances.get(accountId);

            dto.setAmount(amount);

            if (accountTypes.get(accountId)
                    == AccountType.REVENUE) {

                revenues.add(dto);

                totalRevenue += amount;

            }

            else {

                expenses.add(dto);

                totalExpense += amount;

            }

        }

        IncomeStatementResponseDTO response =
                new IncomeStatementResponseDTO();

        response.setFromDate(fromDate);

        response.setToDate(toDate);

        response.setRevenues(revenues);

        response.setExpenses(expenses);

        response.setTotalRevenue(totalRevenue);

        response.setTotalExpense(totalExpense);

        response.setNetProfit(

                totalRevenue - totalExpense

        );

        return response;

    }

    private IncomeStatementEntryResponseDTO createEntry(

            JournalLineEntity line) {

        IncomeStatementEntryResponseDTO dto =
                new IncomeStatementEntryResponseDTO();

        dto.setAccountId(

                line.getAccount().getId()

        );

        dto.setAccountCode(

                line.getAccount().getCode()

        );

        dto.setAccountName(

                line.getAccount().getName()

        );

        return dto;

    }

}