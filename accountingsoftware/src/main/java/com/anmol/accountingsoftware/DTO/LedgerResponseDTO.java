package com.anmol.accountingsoftware.DTO;

import java.time.LocalDate;
import java.util.List;

public class LedgerResponseDTO {

    private String accountName;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double openingBalance;

    private Double closingBalance;

    private String openingBalanceType;

    private String closingBalanceType;

    private List<LedgerEntryResponseDTO> entries;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

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

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Double closingBalance) {
        this.closingBalance = closingBalance;
    }

    public List<LedgerEntryResponseDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<LedgerEntryResponseDTO> entries) {
        this.entries = entries;
    }

    public String getOpeningBalanceType() {
        return openingBalanceType;
    }

    public void setOpeningBalanceType(String openingBalanceType) {
        this.openingBalanceType = openingBalanceType;
    }

    public String getClosingBalanceType() {
        return closingBalanceType;
    }

    public void setClosingBalanceType(String closingBalanceType) {
        this.closingBalanceType = closingBalanceType;
    }
    
}