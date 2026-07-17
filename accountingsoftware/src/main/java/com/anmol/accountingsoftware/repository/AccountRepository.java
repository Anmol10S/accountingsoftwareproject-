package com.anmol.accountingsoftware.repository;

import com.anmol.accountingsoftware.entity.AccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface AccountRepository
        extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByCode(String code);

    Optional<AccountEntity> findByName(String name);

    List<AccountEntity> findByActiveTrue();

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
