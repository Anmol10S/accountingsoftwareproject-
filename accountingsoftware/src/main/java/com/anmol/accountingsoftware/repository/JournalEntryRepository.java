package com.anmol.accountingsoftware.repository;

import com.anmol.accountingsoftware.entity.JournalEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JournalEntryRepository extends JpaRepository<JournalEntryEntity, Long> {
    List<JournalEntryEntity>
findTop5ByActiveTrueOrderByTransactionDateDescIdDesc();
}