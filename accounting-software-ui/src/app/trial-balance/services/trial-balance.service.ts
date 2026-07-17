import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { TrialBalanceResponse } from '../models/trial-balance-response.model';

@Injectable({

    providedIn:'root'

})

export class TrialBalanceService{

    private http=inject(HttpClient);

    private apiUrl='http://localhost:8080/trial-balance';

    getTrialBalance(

        from:string,

        to:string

    ):Observable<TrialBalanceResponse>{

        return this.http.get<TrialBalanceResponse>(

            `${this.apiUrl}?from=${from}&to=${to}`

        );

    }

}