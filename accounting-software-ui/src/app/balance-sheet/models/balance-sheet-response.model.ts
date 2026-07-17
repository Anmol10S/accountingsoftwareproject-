import { BalanceSheetEntry } from "./balance-sheet-entry.model";

export interface BalanceSheetResponse{

    fromDate:string;

    toDate:string;

    assets:BalanceSheetEntry[];

    liabilities:BalanceSheetEntry[];

    equity:BalanceSheetEntry[];

    totalAssets:number;

    totalLiabilities:number;

    totalEquity:number;

    totalLiabilitiesAndEquity:number;

    balanced:boolean;

}