package com.anmol.accountingsoftware.service.accounting;

import com.anmol.accountingsoftware.entity.AccountType;

public class AccountingEngine {

    private AccountingEngine() {
    }

    public static double calculateBalance(

            AccountType accountType,

            double currentBalance,

            double debit,

            double credit) {

        switch (accountType) {

            case ASSET:
            case EXPENSE:

                return currentBalance
                        + debit
                        - credit;

            case LIABILITY:
            case EQUITY:
            case REVENUE:

                return currentBalance
                        + credit
                        - debit;

            default:

                throw new IllegalArgumentException(

                        "Unknown Account Type : "

                                + accountType

                );

        }

    }

}