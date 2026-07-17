import { IncomeStatementEntry } from "./income-statement-entry.model";

export interface IncomeStatementResponse{

    fromDate:string;

    toDate:string;

    revenues:IncomeStatementEntry[];

    expenses:IncomeStatementEntry[];

    totalRevenue:number;

    totalExpense:number;

    netProfit:number;

}