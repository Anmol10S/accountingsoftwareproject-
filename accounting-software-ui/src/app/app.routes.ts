import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/pages/dashboard.component';

import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';

import { AccountComponent } from './account/account.component';

import { JournalListComponent } from './journal/pages/journal-list/journal-list.component';
import { JournalFormComponent } from './journal/pages/journal-form/journal-form.component';

import { LedgerComponent } from './ledger/pages/ledger.component';

import { TrialBalanceComponent } from './trial-balance/pages/trial-balance.component';

import { IncomeStatementComponent } from './income-statement/pages/income-statement.component';

import { BalanceSheetComponent } from './balance-sheet/pages/balance-sheet.component';

export const routes: Routes = [

  {
    path: '',
    component: DashboardLayoutComponent,
    children: [

      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path:'journals',
        component:JournalListComponent
      },
      {
        path: 'journals/new',
        component: JournalFormComponent
      },
      {
        path: 'trial-balance',
        component: TrialBalanceComponent
      },
      {
        path: 'journals/edit/:id',
        component: JournalFormComponent
      },
      {
        path: 'account',
        component: AccountComponent
      },
      {
        path:'ledger',
        component: LedgerComponent
      },
      {
        path:'income-statement',
        component:IncomeStatementComponent
      },
      {
        path:'balance-sheet',
        component:BalanceSheetComponent
      }

    ]
  }

];