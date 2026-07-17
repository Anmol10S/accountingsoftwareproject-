import { Component, OnInit, inject } from '@angular/core';

import { CommonModule, DatePipe } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { TrialBalanceResponse } from '../models/trial-balance-response.model';

import { TrialBalanceService } from '../services/trial-balance.service';

@Component({

    selector:'app-trial-balance',

    standalone:true,

    imports:[
        CommonModule,
        FormsModule,
        DatePipe
    ],

    templateUrl:'./trial-balance.component.html',

    styleUrl:'./trial-balance.component.css'

})

export class TrialBalanceComponent implements OnInit{

    private trialBalanceService=inject(TrialBalanceService);

    trialBalance?:TrialBalanceResponse;

    fromDate='';

    toDate='';

    ngOnInit():void{

        this.setDefaultDates();

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

    checkAndLoadTrialBalance():void{

        if(

            this.fromDate &&

            this.toDate

        ){

            this.loadTrialBalance();

        }

    }

    loadTrialBalance():void{

        this.trialBalanceService

            .getTrialBalance(

                this.fromDate,

                this.toDate

            )

            .subscribe({

                next:(data)=>{

                    this.trialBalance=data;

                },

                error:(err)=>{

                    console.error(err);

                }

            });

    }

}