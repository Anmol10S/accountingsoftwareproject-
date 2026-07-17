package com.anmol.accountingsoftware.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anmol.accountingsoftware.DTO.JournalEntryDTO;
import com.anmol.accountingsoftware.DTO.JournalEntryResponseDTO;
import com.anmol.accountingsoftware.DTO.JournalLineDTO;
import com.anmol.accountingsoftware.DTO.JournalLineResponseDTO;
import com.anmol.accountingsoftware.entity.AccountEntity;
import com.anmol.accountingsoftware.entity.JournalEntryEntity;
import com.anmol.accountingsoftware.entity.JournalLineEntity;
import com.anmol.accountingsoftware.repository.AccountRepository;
import com.anmol.accountingsoftware.repository.JournalEntryRepository;

@Service
public class JournalService {

    private final JournalEntryRepository journalEntryRepository;
    private final AccountRepository accountRepository;

    public JournalService(
            JournalEntryRepository journalEntryRepository,
            AccountRepository accountRepository) {

        this.journalEntryRepository = journalEntryRepository;
        this.accountRepository = accountRepository;
    }

    // =====================================================
    // CREATE
    // =====================================================

    @Transactional
    public JournalEntryResponseDTO createJournalEntry(JournalEntryDTO journalEntryDTO) {

        double totalCredit = 0;
        double totalDebit = 0;

        for (JournalLineDTO lineDTO : journalEntryDTO.getJournalLines()) {
            totalCredit += lineDTO.getCredit();
            totalDebit += lineDTO.getDebit();
        }

        if (Math.abs(totalCredit - totalDebit) > 0.0001) {
            throw new RuntimeException("Journal Entry is not balanced.");
        }

        JournalEntryEntity journalEntryEntity = new JournalEntryEntity();

        journalEntryEntity.setTransactionDate(
                journalEntryDTO.getTransactionDate());

        journalEntryEntity.setDescription(
                journalEntryDTO.getDescription());

        journalEntryEntity.setReferenceNo(generateReferenceNo());

        for (JournalLineDTO lineDTO : journalEntryDTO.getJournalLines()) {

            AccountEntity account = accountRepository
                    .findById(lineDTO.getAccountId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Account not found with id : "
                                            + lineDTO.getAccountId()));

            JournalLineEntity line = new JournalLineEntity();

            line.setAccount(account);
            line.setDebit(lineDTO.getDebit());
            line.setCredit(lineDTO.getCredit());

            journalEntryEntity.addJournalLine(line);
        }

        JournalEntryEntity savedJournal =
                journalEntryRepository.save(journalEntryEntity);

        return convertToResponse(savedJournal);
    }
    //GENERATE REFERENCE NUMBER
    public String generateReferenceNo() {

    JournalEntryEntity latest =
            journalEntryRepository.findTop5ByActiveTrueOrderByTransactionDateDescIdDesc().get(0);

    if (latest == null) {
        return "JV000001";
    }

    String last = latest.getReferenceNo();

    int number = Integer.parseInt(last.substring(2));

    return String.format("JV%06d", number + 1);

}

    // =====================================================
    // GET BY ID
    // =====================================================

    public JournalEntryResponseDTO getJournalById(Long id) {

        JournalEntryEntity journal =
                journalEntryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Journal not found."));

        return convertToResponse(journal);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    public List<JournalEntryResponseDTO> getAllJournals() {

        List<JournalEntryResponseDTO> response =
                new ArrayList<>();

        List<JournalEntryEntity> journals =
                journalEntryRepository.findAll();

        for (JournalEntryEntity journal : journals) {
            response.add(convertToResponse(journal));
        }

        return response;
    }

    // =====================================================
    // SOFT DELETE
    // =====================================================

    @Transactional
    public void deactivateJournal(Long id) {

        JournalEntryEntity journal =
                journalEntryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Journal not found."));

        journal.setActive(false);

        journalEntryRepository.save(journal);
    }

    // =====================================================
    // ENTITY -> RESPONSE DTO
    // =====================================================

    private JournalEntryResponseDTO convertToResponse(
        JournalEntryEntity journalEntryEntity) {

    JournalEntryResponseDTO response =
            new JournalEntryResponseDTO();

    response.setId(journalEntryEntity.getId());
    response.setReferenceNo(journalEntryEntity.getReferenceNo());
    response.setTransactionDate(
            journalEntryEntity.getTransactionDate());
    response.setDescription(
            journalEntryEntity.getDescription());

    response.setActive(journalEntryEntity.isActive());
    response.setCreatedAt(journalEntryEntity.getCreatedAt());
    response.setCreatedBy(journalEntryEntity.getCreatedBy());
    response.setUpdatedAt(journalEntryEntity.getUpdatedAt());
    response.setUpdatedBy(journalEntryEntity.getUpdatedBy());

    List<JournalLineResponseDTO> lineResponses =
            new ArrayList<>();

    double totalDebit = 0;
    double totalCredit = 0;

    for (JournalLineEntity line :
            journalEntryEntity.getJournalLines()) {

        JournalLineResponseDTO lineResponse =
                new JournalLineResponseDTO();

        lineResponse.setId(line.getId());

        lineResponse.setAccountId(
                line.getAccount().getId());

        lineResponse.setAccountName(
                line.getAccount().getName());

        lineResponse.setDebit(line.getDebit());

        lineResponse.setCredit(line.getCredit());

        lineResponse.setActive(line.isActive());
        lineResponse.setCreatedAt(line.getCreatedAt());
        lineResponse.setCreatedBy(line.getCreatedBy());
        lineResponse.setUpdatedAt(line.getUpdatedAt());
        lineResponse.setUpdatedBy(line.getUpdatedBy());

        lineResponses.add(lineResponse);

        totalDebit += line.getDebit();
        totalCredit += line.getCredit();
    }

    response.setLines(lineResponses);

    response.setTotalDebit(totalDebit);
    response.setTotalCredit(totalCredit);

    return response;
}

}