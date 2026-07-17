package com.anmol.accountingsoftware.service;

import com.anmol.accountingsoftware.DTO.BalanceSheetEntryResponseDTO;
import com.anmol.accountingsoftware.DTO.BalanceSheetResponseDTO;
import com.anmol.accountingsoftware.DTO.IncomeStatementResponseDTO;
import com.anmol.accountingsoftware.entity.AccountType;
import com.anmol.accountingsoftware.entity.JournalLineEntity;
import com.anmol.accountingsoftware.repository.JournalLineRepository;
import com.anmol.accountingsoftware.service.accounting.AccountingEngine;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BalanceSheetService {

    private final JournalLineRepository journalLineRepository;

    private final IncomeStatementService incomeStatementService;

    public BalanceSheetService(

            JournalLineRepository journalLineRepository,

            IncomeStatementService incomeStatementService) {

        this.journalLineRepository = journalLineRepository;

        this.incomeStatementService = incomeStatementService;

    }

    public BalanceSheetResponseDTO getBalanceSheet(

            LocalDate fromDate,

            LocalDate toDate) {

        List<JournalLineEntity> journalLines =

                journalLineRepository.getTrialBalanceLines(

                        fromDate,

                        toDate

                );

        Map<Long, Double> balances = new LinkedHashMap<>();

        Map<Long, BalanceSheetEntryResponseDTO> accountMap =
                new LinkedHashMap<>();

        Map<Long, AccountType> accountTypes =
                new HashMap<>();

        for (JournalLineEntity line : journalLines) {

            AccountType type = line.getAccount().getType();

            if (type == AccountType.REVENUE ||
                    type == AccountType.EXPENSE) {

                continue;

            }

            Long accountId = line.getAccount().getId();

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

            accountTypes.put(

                    accountId,

                    type

            );

            accountMap.putIfAbsent(

                    accountId,

                    createEntry(line)

            );

        }

        List<BalanceSheetEntryResponseDTO> assets =
                new ArrayList<>();

        List<BalanceSheetEntryResponseDTO> liabilities =
                new ArrayList<>();

        List<BalanceSheetEntryResponseDTO> equity =
                new ArrayList<>();

        double totalAssets = 0;

        double totalLiabilities = 0;

        double totalEquity = 0;

        for (Long accountId : accountMap.keySet()) {

            BalanceSheetEntryResponseDTO dto =
                    accountMap.get(accountId);

            double amount =
                    balances.get(accountId);

            dto.setAmount(amount);

            AccountType type =
                    accountTypes.get(accountId);

            switch (type) {

                case ASSET:

                    assets.add(dto);

                    totalAssets += amount;

                    break;

                case LIABILITY:

                    liabilities.add(dto);

                    totalLiabilities += amount;

                    break;

                case EQUITY:

                    equity.add(dto);

                    totalEquity += amount;

                    break;

            }

        }

        IncomeStatementResponseDTO incomeStatement =

                incomeStatementService.getIncomeStatement(

                        fromDate,

                        toDate

                );

        double netProfit =
                incomeStatement.getNetProfit();

        if (netProfit != 0) {

            BalanceSheetEntryResponseDTO profitEntry =
                    new BalanceSheetEntryResponseDTO();

            profitEntry.setAccountName(
                    "Current Year Profit");

            profitEntry.setAmount(netProfit);

            equity.add(profitEntry);

            totalEquity += netProfit;

        }

        BalanceSheetResponseDTO response =
                new BalanceSheetResponseDTO();

        response.setFromDate(fromDate);

        response.setToDate(toDate);

        response.setAssets(assets);

        response.setLiabilities(liabilities);

        response.setEquity(equity);

        response.setTotalAssets(totalAssets);

        response.setTotalLiabilities(totalLiabilities);

        response.setTotalEquity(totalEquity);

        response.setTotalLiabilitiesAndEquity(

                totalLiabilities +

                        totalEquity

        );

        response.setBalanced(

                Math.abs(

                        totalAssets -

                                (

                                        totalLiabilities +

                                                totalEquity

                                )

                ) < 0.001

        );

        return response;

    }

    private BalanceSheetEntryResponseDTO createEntry(

            JournalLineEntity line) {

        BalanceSheetEntryResponseDTO dto =
                new BalanceSheetEntryResponseDTO();

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