import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JournalLineTableComponent } from '../../components/journal-line-table/journal-line-table.component';
import { Router } from '@angular/router';
import { inject } from '@angular/core';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { JournalService } from '../../services/journal.service';

import {
    FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

@Component({
  selector: 'app-journal-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    JournalLineTableComponent,
    MatSnackBarModule
  ],
  templateUrl: './journal-form.component.html',
  styleUrl: './journal-form.component.css'
})
export class JournalFormComponent {

  journalForm: FormGroup;
  totalDebit = 0;
  totalCredit = 0;
  isBalanced = true;

  private journalService = inject(JournalService);

  private router = inject(Router);

  private snackBar = inject(MatSnackBar);

  constructor(private fb: FormBuilder) {

    this.journalForm = this.fb.group({

        transactionDate: ['', Validators.required],

        description: ['', Validators.required],

        referenceNo: [
            {
                value: '',
                disabled: true
            }
        ],

        journalLines: this.fb.array([])

    });

    this.addLine();
    this.calculateTotals();
    
    this.journalLines.valueChanges.subscribe(() => {

    this.calculateTotals();

});

}
calculateTotals(): void {

    this.totalDebit = 0;

    this.totalCredit = 0;

    this.journalLines.controls.forEach(line => {

        const debit =
            Number(line.get('debit')?.value) || 0;

        const credit =
            Number(line.get('credit')?.value) || 0;

        this.totalDebit += debit;

        this.totalCredit += credit;

    });

    this.isBalanced =
        this.totalDebit === this.totalCredit;

}
get journalLines(): FormArray {

    return this.journalForm.get('journalLines') as FormArray;

}

addLine() {

    this.journalLines.push(

        this.fb.group({

            accountId: [''],

            debit: [0],

            credit: [0]

        })

    );

}

deleteLine(index:number){

    this.journalLines.removeAt(index);

}

  saveJournal(): void {

    if (!this.isBalanced) {

        alert("Journal Entry is not balanced.");

        return;

    }

    const journal = this.journalForm.getRawValue();

    this.journalService.createJournal(journal).subscribe({

        next: () => {

            this.snackBar.open(

    "Journal Entry Saved Successfully",

    "Close",

    {

        duration:3000,

        horizontalPosition:'right',

        verticalPosition:'top'

    }

);

            this.router.navigate(['/journals']);

        },

        error: (err) => {

            console.error(err);

            this.snackBar.open(

    "Failed to save Journal Entry",

    "Close",

    {

        duration:3000,

        horizontalPosition:'right',

        verticalPosition:'top'

    }

);

        }

    });

}

}