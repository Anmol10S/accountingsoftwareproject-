package com.anmol.accountingsoftware.domain;
import java.util.List;
import com.anmol.accountingsoftware.entity.JournalLineEntity;

public class AccountBalance {
    Long accountId;

    String accountCode;

    String accountName;

    Double openingBalance;

    Double closingBalance;

    List<JournalLineEntity> journalLines;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public List<JournalLineEntity> getJournalLines() {
        return journalLines;
    }

    public void setJournalLines(List<JournalLineEntity> journalLines) {
        this.journalLines = journalLines;
    }

    

}
