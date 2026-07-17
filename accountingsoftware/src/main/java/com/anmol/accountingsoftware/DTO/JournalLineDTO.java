package com.anmol.accountingsoftware.DTO;

public class JournalLineDTO {

    private Double debit;

    private Double credit;

    private Long accountId;

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    // ================= GETTERS AND SETTERS =================
    
}
