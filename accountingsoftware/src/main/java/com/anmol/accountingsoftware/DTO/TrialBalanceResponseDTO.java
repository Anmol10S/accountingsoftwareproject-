package com.anmol.accountingsoftware.DTO;

import java.time.LocalDate;
import java.util.List;

public class TrialBalanceResponseDTO {

    private LocalDate fromDate;

    private LocalDate toDate;

    private List<TrialBalanceEntryResponseDTO> entries;

    private Double totalDebit;

    private Double totalCredit;

    private boolean balanced;

    // ===== GETTERS & SETTERS =====

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public List<TrialBalanceEntryResponseDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<TrialBalanceEntryResponseDTO> entries) {
        this.entries = entries;
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

    public boolean isBalanced() {
        return balanced;
    }

    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }

}