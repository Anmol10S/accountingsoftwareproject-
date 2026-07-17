import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { LedgerResponse } from '../models/ledger-response.model';

@Injectable({

    providedIn:'root'

})

export class LedgerService{

    private http = inject(HttpClient);

    private apiUrl='http://localhost:8080/ledger';

    getLedger(

        accountId:number,

        from:string,

        to:string

    ):Observable<LedgerResponse>{

        return this.http.get<LedgerResponse>(

            `${this.apiUrl}?accountId=${accountId}&from=${from}&to=${to}`

        );

    }

}