package com.anmol.accountingsoftware.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.anmol.accountingsoftware.service.JournalService;
import com.anmol.accountingsoftware.DTO.JournalEntryDTO;
import com.anmol.accountingsoftware.DTO.JournalEntryResponseDTO;

@RestController
@RequestMapping("/journals")
public class JournalController {
    private final JournalService journalService;
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }
    //CREATE JOURNAL ENTRY
    @PostMapping
    public ResponseEntity<JournalEntryResponseDTO> createJournalEntry(@RequestBody JournalEntryDTO journalEntryDTO) {
        JournalEntryResponseDTO response = journalService.createJournalEntry(journalEntryDTO);
        return ResponseEntity.ok(response);
    }
     // =====================================================
    // GET JOURNAL BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntryResponseDTO> getJournalById(
            @PathVariable Long id) {

        JournalEntryResponseDTO response =
                journalService.getJournalById(id);

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // GET ALL JOURNALS
    // =====================================================

    @GetMapping
    public ResponseEntity<List<JournalEntryResponseDTO>> getAllJournals() {

        List<JournalEntryResponseDTO> response =
                journalService.getAllJournals();

        return ResponseEntity.ok(response);
    }

    // =====================================================
    // SOFT DELETE JOURNAL
    // =====================================================

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateJournal(
            @PathVariable Long id) {

        journalService.deactivateJournal(id);

        return ResponseEntity.ok("Journal Entry deactivated successfully.");
    }

}
