import { Component, OnInit, inject } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Account } from '../../account/account.model';
import { AccountService } from '../../account/account.service';

import { LedgerResponse } from '../models/ledger-response.model';
import { LedgerService } from '../services/ledger.service';

@Component({

    selector: 'app-ledger',

    standalone: true,

    imports: [
        CommonModule,
        FormsModule,
        DatePipe
    ],

    templateUrl: './ledger.component.html',

    styleUrl: './ledger.component.css'

})

export class LedgerComponent implements OnInit {

    private accountService = inject(AccountService);

    private ledgerService = inject(LedgerService);

    accounts: Account[] = [];

    ledger?: LedgerResponse;

    selectedAccountId = 0;

    fromDate = '';

    toDate = '';

    ngOnInit(): void {

        this.loadAccounts();

        this.setDefaultDates();

    }

    setDefaultDates(): void {

        const today = new Date();

        const firstDay = new Date(
            today.getFullYear(),
            today.getMonth(),
            1
        );

        this.fromDate =
            firstDay.toISOString().split('T')[0];

        this.toDate =
            today.toISOString().split('T')[0];

    }

    loadAccounts(): void {

        this.accountService.getAllAccounts().subscribe({

            next: (data) => {

                this.accounts = data;

            }

        });

    }

    checkAndLoadLedger(): void {

        if (

            this.selectedAccountId &&
            this.fromDate &&
            this.toDate

        ) {

            this.loadLedger();

        }

    }

    loadLedger(): void {

        this.ledgerService

            .getLedger(

                this.selectedAccountId,

                this.fromDate,

                this.toDate

            )

            .subscribe({

                next: (data) => {

                    this.ledger = data;

                },

                error: (err) => {

                    console.error(err);

                }

            });

    }

}