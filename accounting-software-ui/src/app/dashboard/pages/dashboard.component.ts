import { Component, OnInit, inject } from '@angular/core';

import { CommonModule, DatePipe } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { DashboardService } from '../services/dashboard.service';

import { DashboardResponse } from '../models/dashboard-response.model';

@Component({

    selector:'app-dashboard',

    standalone:true,

    imports:[
        CommonModule,
        FormsModule,
        DatePipe
    ],

    templateUrl:'./dashboard.component.html',

    styleUrl:'./dashboard.component.css'

})

export class DashboardComponent implements OnInit{

    private dashboardService=

        inject(DashboardService);

    dashboard?:DashboardResponse;

    fromDate='';

    toDate='';

    ngOnInit():void{

        this.setDefaultDates();

        this.loadDashboard();

    }

    setDefaultDates():void{

        const today=new Date();

        const firstDay=new Date(

            today.getFullYear(),

            today.getMonth(),

            1

        );

        this.fromDate=

            firstDay.toISOString().split('T')[0];

        this.toDate=

            today.toISOString().split('T')[0];

    }

    loadDashboard():void{

        this.dashboardService

            .getDashboard(

                this.fromDate,

                this.toDate

            )

            .subscribe({

                next:(data)=>{

                    this.dashboard=data;

                },

                error:(err)=>{

                    console.error(err);

                }

            });

    }

    checkAndLoadDashboard():void{

        if(

            this.fromDate &&

            this.toDate

        ){

            this.loadDashboard();

        }

    }

}