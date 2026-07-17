
import { TrialBalanceEntry } from "./trial-balance-entry.model";

export interface TrialBalanceResponse{

    fromDate:string;

    toDate:string;

    entries:TrialBalanceEntry[];

    totalDebit:number;

    totalCredit:number;

    balanced:boolean;

}