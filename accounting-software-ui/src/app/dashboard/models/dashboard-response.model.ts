import { DashboardJournal } from "./dashboard-journal.model";

export interface DashboardResponse{

    fromDate:string;

    toDate:string;

    totalAssets:number;

    totalLiabilities:number;

    totalEquity:number;

    revenue:number;

    expenses:number;

    netProfit:number;

    totalAccounts:number;

    totalJournalEntries:number;

    recentJournals:DashboardJournal[];

}