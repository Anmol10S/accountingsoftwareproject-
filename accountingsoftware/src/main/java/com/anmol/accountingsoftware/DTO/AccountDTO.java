package com.anmol.accountingsoftware.DTO;

import com.anmol.accountingsoftware.entity.AccountType;

public class AccountDTO {

    private String code;

    private String name;

    private AccountType type;
    // ===== Getters and Setters =====

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    
}
