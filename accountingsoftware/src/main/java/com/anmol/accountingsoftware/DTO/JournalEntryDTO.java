package com.anmol.accountingsoftware.DTO;

import java.time.LocalDate;

import java.util.List;

public class JournalEntryDTO {

    private LocalDate transactionDate;

    private String description;

    private List<JournalLineDTO> journalLines;
    //GETTERS AND SETTERS

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JournalLineDTO> getJournalLines() {
        return journalLines;
    }

    public void setJournalLines(List<JournalLineDTO> journalLines) {
        this.journalLines = journalLines;
    }
    
}
