import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { DashboardResponse } from '../models/dashboard-response.model';

@Injectable({

    providedIn:'root'

})

export class DashboardService{

    private http=inject(HttpClient);

    private apiUrl='http://localhost:8080/dashboard';

    getDashboard(

        from:string,

        to:string

    ):Observable<DashboardResponse>{

        return this.http.get<DashboardResponse>(

            `${this.apiUrl}?from=${from}&to=${to}`

        );

    }

}