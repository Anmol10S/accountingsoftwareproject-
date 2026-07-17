import { JournalLine } from './journal-line.model';

export interface JournalEntry {

    id?: number;

    referenceNo?: string;

    transactionDate: string;

    description: string;

    lines: JournalLine[];

    totalDebit?: number;

    totalCredit?: number;

    active?: boolean;

    createdAt?: string;

    createdBy?: string;

    updatedAt?: string;

    updatedBy?: string;

}