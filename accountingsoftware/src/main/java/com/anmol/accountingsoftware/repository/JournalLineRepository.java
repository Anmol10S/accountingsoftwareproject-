package com.anmol.accountingsoftware.repository;

import com.anmol.accountingsoftware.entity.AccountEntity;
import com.anmol.accountingsoftware.entity.JournalLineEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JournalLineRepository extends JpaRepository<JournalLineEntity, Long> {

    List<JournalLineEntity> findByAccount(AccountEntity account);

    List<JournalLineEntity>
    findByAccountIdAndActiveTrueOrderByJournalEntryTransactionDateAsc(
            Long accountId);

    List<JournalLineEntity>
    findByAccountIdAndActiveTrueAndJournalEntryTransactionDateBetweenOrderByJournalEntryTransactionDateAsc(
            Long accountId,
            LocalDate fromDate,
            LocalDate toDate
    );

    List<JournalLineEntity>
    findByAccountIdAndActiveTrueAndJournalEntryTransactionDateBefore(
            Long accountId,
            LocalDate fromDate
    );

    @Query("""
            SELECT jl
            FROM JournalLineEntity jl
            WHERE jl.active = true
            AND jl.journalEntry.transactionDate BETWEEN :fromDate AND :toDate
            ORDER BY
                jl.account.code,
                jl.journalEntry.transactionDate,
                jl.journalEntry.id,
                jl.id
            """)
    List<JournalLineEntity> getTrialBalanceLines(

            @Param("fromDate") LocalDate fromDate,

            @Param("toDate") LocalDate toDate

    );
    @Query("""
        SELECT jl
        FROM JournalLineEntity jl
        WHERE jl.active = true
        AND jl.journalEntry.transactionDate BETWEEN :fromDate AND :toDate
        ORDER BY
        jl.account.type,
        jl.account.code,
        jl.journalEntry.transactionDate,
        jl.id
        """)
        List<JournalLineEntity> getIncomeStatementLines(

                @Param("fromDate") LocalDate fromDate,

                @Param("toDate") LocalDate toDate

        );

}