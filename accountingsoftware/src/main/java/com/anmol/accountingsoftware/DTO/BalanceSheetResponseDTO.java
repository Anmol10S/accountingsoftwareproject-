package com.anmol.accountingsoftware.DTO;

import java.time.LocalDate;
import java.util.List;

public class BalanceSheetResponseDTO {

    private LocalDate fromDate;

    private LocalDate toDate;

    private List<BalanceSheetEntryResponseDTO> assets;

    private List<BalanceSheetEntryResponseDTO> liabilities;

    private List<BalanceSheetEntryResponseDTO> equity;

    private Double totalAssets;

    private Double totalLiabilities;

    private Double totalEquity;

    private Double totalLiabilitiesAndEquity;

    private boolean balanced;

    // ================= GETTERS & SETTERS =================

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

    public List<BalanceSheetEntryResponseDTO> getAssets() {
        return assets;
    }

    public void setAssets(List<BalanceSheetEntryResponseDTO> assets) {
        this.assets = assets;
    }

    public List<BalanceSheetEntryResponseDTO> getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(List<BalanceSheetEntryResponseDTO> liabilities) {
        this.liabilities = liabilities;
    }

    public List<BalanceSheetEntryResponseDTO> getEquity() {
        return equity;
    }

    public void setEquity(List<BalanceSheetEntryResponseDTO> equity) {
        this.equity = equity;
    }

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getTotalLiabilitiesAndEquity() {
        return totalLiabilitiesAndEquity;
    }

    public void setTotalLiabilitiesAndEquity(Double totalLiabilitiesAndEquity) {
        this.totalLiabilitiesAndEquity = totalLiabilitiesAndEquity;
    }

    public boolean isBalanced() {
        return balanced;
    }

    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }

}