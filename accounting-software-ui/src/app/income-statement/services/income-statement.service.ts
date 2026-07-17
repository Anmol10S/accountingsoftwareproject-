import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { IncomeStatementResponse } from '../models/income-statement-response.model';

@Injectable({

    providedIn:'root'

})

export class IncomeStatementService{

    private http=inject(HttpClient);

    private apiUrl='http://localhost:8080/income-statement';

    getIncomeStatement(

        from:string,

        to:string

    ):Observable<IncomeStatementResponse>{

        return this.http.get<IncomeStatementResponse>(

            `${this.apiUrl}?from=${from}&to=${to}`

        );

    }

}