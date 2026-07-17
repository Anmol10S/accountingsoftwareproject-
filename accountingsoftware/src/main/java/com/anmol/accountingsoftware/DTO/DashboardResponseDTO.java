package com.anmol.accountingsoftware.DTO;

import java.time.LocalDate;
import java.util.List;

public class DashboardResponseDTO {

    private LocalDate fromDate;
    private LocalDate toDate;

    private Double totalAssets;
    private Double totalLiabilities;
    private Double totalEquity;

    private Double revenue;
    private Double expenses;
    private Double netProfit;

    private Long totalAccounts;
    private Long totalJournalEntries;

    private List<DashboardJournalDTO> recentJournals;

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

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Long getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(Long totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public Long getTotalJournalEntries() {
        return totalJournalEntries;
    }

    public void setTotalJournalEntries(Long totalJournalEntries) {
        this.totalJournalEntries = totalJournalEntries;
    }

    public List<DashboardJournalDTO> getRecentJournals() {
        return recentJournals;
    }

    public void setRecentJournals(List<DashboardJournalDTO> recentJournals) {
        this.recentJournals = recentJournals;
    }
}