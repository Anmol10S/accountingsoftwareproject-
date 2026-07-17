import { LedgerEntry } from './ledger-entry.model';

export interface LedgerResponse {

    accountName: string;

    fromDate: string;

    toDate: string;

    openingBalance: number;

    closingBalance: number;

    entries: LedgerEntry[];

    openingBalanceType:string;

    closingBalanceType:string;

}