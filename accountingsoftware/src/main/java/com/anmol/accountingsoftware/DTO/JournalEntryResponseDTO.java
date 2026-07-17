package com.anmol.accountingsoftware.DTO;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

public class JournalEntryResponseDTO {

    private Long id;

    private String referenceNo;

    private LocalDate transactionDate;

    private String description;

    private List<JournalLineResponseDTO> lines;

    private Double totalDebit;

    private Double totalCredit;

    // Audit Fields

    private boolean active;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // ================= GETTERS AND SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public List<JournalLineResponseDTO> getLines() {
        return lines;
    }

    public void setLines(List<JournalLineResponseDTO> lines) {
        this.lines = lines;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }
    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }
    public Double getTotalCredit() {
        return totalCredit;
    }
    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }
}