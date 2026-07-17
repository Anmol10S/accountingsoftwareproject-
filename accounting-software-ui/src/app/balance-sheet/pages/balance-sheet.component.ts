import { Component, OnInit, inject } from '@angular/core';

import { CommonModule, DatePipe } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { BalanceSheetService } from '../services/balance-sheet.service';

import { BalanceSheetResponse } from '../models/balance-sheet-response.model';

@Component({

    selector:'app-balance-sheet',

    standalone:true,

    imports:[
        CommonModule,
        FormsModule,
        DatePipe
    ],

    templateUrl:'./balance-sheet.component.html',

    styleUrl:'./balance-sheet.component.css'

})

export class BalanceSheetComponent implements OnInit{

    private balanceSheetService=

        inject(BalanceSheetService);

    balanceSheet?:BalanceSheetResponse;

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

    checkAndLoadBalanceSheet():void{

        if(

            this.fromDate &&

            this.toDate

        ){

            this.loadBalanceSheet();

        }

    }

    loadBalanceSheet():void{

        this.balanceSheetService

            .getBalanceSheet(

                this.fromDate,

                this.toDate

            )

            .subscribe({

                next:(data)=>{

                    this.balanceSheet=data;

                },

                error:(err)=>{

                    console.error(err);

                }

            });

    }

}