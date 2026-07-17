package com.anmol.accountingsoftware.service;

import com.anmol.accountingsoftware.DTO.*;
import com.anmol.accountingsoftware.entity.JournalEntryEntity;
import com.anmol.accountingsoftware.repository.AccountRepository;
import com.anmol.accountingsoftware.repository.JournalEntryRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final BalanceSheetService balanceSheetService;

    private final IncomeStatementService incomeStatementService;

    private final AccountRepository accountRepository;

    private final JournalEntryRepository journalEntryRepository;

    public DashboardService(

            BalanceSheetService balanceSheetService,

            IncomeStatementService incomeStatementService,

            AccountRepository accountRepository,

            JournalEntryRepository journalEntryRepository

    ) {

        this.balanceSheetService = balanceSheetService;

        this.incomeStatementService = incomeStatementService;

        this.accountRepository = accountRepository;

        this.journalEntryRepository = journalEntryRepository;

    }

    public DashboardResponseDTO getDashboard(

            LocalDate fromDate,

            LocalDate toDate

    ) {

        BalanceSheetResponseDTO balanceSheet =

                balanceSheetService.getBalanceSheet(

                        fromDate,

                        toDate

                );

        IncomeStatementResponseDTO incomeStatement =

                incomeStatementService.getIncomeStatement(

                        fromDate,

                        toDate

                );

        DashboardResponseDTO response =
                new DashboardResponseDTO();

        response.setFromDate(fromDate);

        response.setToDate(toDate);

        response.setTotalAssets(

                balanceSheet.getTotalAssets()

        );

        response.setTotalLiabilities(

                balanceSheet.getTotalLiabilities()

        );

        response.setTotalEquity(

                balanceSheet.getTotalEquity()

        );

        response.setRevenue(

                incomeStatement.getTotalRevenue()

        );

        response.setExpenses(

                incomeStatement.getTotalExpense()

        );

        response.setNetProfit(

                incomeStatement.getNetProfit()

        );

        response.setTotalAccounts(

                accountRepository.count()

        );

        response.setTotalJournalEntries(

                journalEntryRepository.count()

        );

        List<JournalEntryEntity> journals =

                journalEntryRepository
                        .findTop5ByActiveTrueOrderByTransactionDateDescIdDesc();

        List<DashboardJournalDTO> recent =
                new ArrayList<>();

        for (JournalEntryEntity journal : journals) {

            DashboardJournalDTO dto =
                    new DashboardJournalDTO();

            dto.setReferenceNo(

                    journal.getReferenceNo()

            );

            dto.setTransactionDate(

                    journal.getTransactionDate()

            );

            dto.setDescription(

                    journal.getDescription()

            );

            recent.add(dto);

        }

        response.setRecentJournals(

                recent

        );

        return response;

    }

}