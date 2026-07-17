import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { BalanceSheetResponse } from '../models/balance-sheet-response.model';

@Injectable({

    providedIn:'root'

})

export class BalanceSheetService{

    private http=inject(HttpClient);

    private apiUrl='http://localhost:8080/balance-sheet';

    getBalanceSheet(

        from:string,

        to:string

    ):Observable<BalanceSheetResponse>{

        return this.http.get<BalanceSheetResponse>(

            `${this.apiUrl}?from=${from}&to=${to}`

        );

    }

}