import { Component, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';

import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';

import { MatButtonModule } from '@angular/material/button';

import { MatInputModule } from '@angular/material/input';

import { MatSelectModule } from '@angular/material/select';

import { MatCheckboxModule } from '@angular/material/checkbox';

import { CommonModule } from '@angular/common';

import { AccountType } from '../../account-type.enum';

import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-account-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule
  ],
  templateUrl: './account-dialog.component.html',
  styleUrl: './account-dialog.component.css'
})
export class AccountDialogComponent {

  accountForm: FormGroup;

  accountTypes = Object.values(AccountType);

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AccountDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
) {
    this.accountForm = this.fb.group({

      code: [
        data?.code || '',
        Validators.required
      ],

      name: [
        data?.name || '',
        Validators.required
      ],

      type: [
        data?.type || '',
        Validators.required
      ],

      active: [
        data?.active ?? true
      ]

    });

  }

  save() {

    if(this.accountForm.invalid){
        return;
    }

    this.dialogRef.close(this.accountForm.value);

}

}