package com.anmol.accountingsoftware.DTO;

import java.time.LocalDate;
import java.util.List;

public class IncomeStatementResponseDTO {

    private LocalDate fromDate;

    private LocalDate toDate;

    private List<IncomeStatementEntryResponseDTO> revenues;

    private List<IncomeStatementEntryResponseDTO> expenses;

    private Double totalRevenue;

    private Double totalExpense;

    private Double netProfit;

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

    public List<IncomeStatementEntryResponseDTO> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<IncomeStatementEntryResponseDTO> revenues) {
        this.revenues = revenues;
    }

    public List<IncomeStatementEntryResponseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<IncomeStatementEntryResponseDTO> expenses) {
        this.expenses = expenses;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

}