import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Account } from './account.model';
import { AccountService } from './account.service';

import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AccountDialogComponent } from './dialogs/account-dialog/account-dialog.component';
import { DeleteDialogComponent } from './dialogs/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [CommonModule,MatDialogModule],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent implements OnInit {

  private accountService = inject(AccountService);
  dialog = inject(MatDialog);
  accounts: Account[] = [];

  ngOnInit(): void {
    this.loadAccounts();
  }
  openCreateDialog() {

    const dialogRef = this.dialog.open(AccountDialogComponent,{

        width:'500px'

    });

    dialogRef.afterClosed().subscribe(result=>{

        if(result){

            this.accountService.createAccount(result).subscribe({

                next:()=>{

                    this.loadAccounts();

                },

                error:(err)=>{

                    console.error(err);

                }

            });

        }

    });

}
openDeleteDialog(account: Account) {

  const dialogRef = this.dialog.open(DeleteDialogComponent, {
    width: '400px',
    data: account
  });

  dialogRef.afterClosed().subscribe(result => {

    if (result) {

      this.accountService
        .deactivateAccount(account.id!)
        .subscribe({

          next: () => {

            this.loadAccounts();

          }

        });

    }

  });

}
openEditDialog(account: Account) {

  const dialogRef = this.dialog.open(AccountDialogComponent, {
    width: '500px',
    data: account
  });

  dialogRef.afterClosed().subscribe(result => {

    if (result) {

      this.accountService
        .updateAccount(account.id!, result)
        .subscribe({

          next: () => {

            this.loadAccounts();

          },

          error: err => console.error(err)

        });

    }

  });

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

}