import { Component, Input, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule
} from '@angular/forms';

import { AccountService } from '../../../account/account.service';
import { Account } from '../../../account/account.model';

@Component({
  selector: 'app-journal-line-table',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './journal-line-table.component.html',
  styleUrl: './journal-line-table.component.css'
})
export class JournalLineTableComponent implements OnInit {

  @Input()
  journalLines!: FormArray;

  private accountService = inject(AccountService);

  accounts: Account[] = [];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {

    this.loadAccounts();

  }

  loadAccounts(): void {

    this.accountService.getAllAccounts().subscribe({

      next: (data) => {

        this.accounts = data;

      },

      error: (err) => {

        console.error(err);

      }

    });

  }

  addLine(): void {

    this.journalLines.push(

      this.fb.group({

        accountId: [''],

        debit: [0],

        credit: [0]

      })

    );

  }

  deleteLine(index: number): void {

    this.journalLines.removeAt(index);

  }

  get lines(): FormGroup[] {

    return this.journalLines.controls as FormGroup[];

  }

}