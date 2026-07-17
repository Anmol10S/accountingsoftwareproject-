import { Component, OnInit, inject } from '@angular/core';

import { CommonModule, DatePipe } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { IncomeStatementService } from '../services/income-statement.service';

import { IncomeStatementResponse } from '../models/income-statement-response.model';

@Component({

    selector:'app-income-statement',

    standalone:true,

    imports:[
        CommonModule,
        FormsModule,
        DatePipe
    ],

    templateUrl:'./income-statement.component.html',

    styleUrl:'./income-statement.component.css'

})

export class IncomeStatementComponent implements OnInit{

    private incomeStatementService=inject(

        IncomeStatementService

    );

    incomeStatement?:IncomeStatementResponse;

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

    checkAndLoadIncomeStatement():void{

        if(

            this.fromDate &&

            this.toDate

        ){

            this.loadIncomeStatement();

        }

    }

    loadIncomeStatement():void{

        this.incomeStatementService

            .getIncomeStatement(

                this.fromDate,

                this.toDate

            )

            .subscribe({

                next:(data)=>{

                    this.incomeStatement=data;

                },

                error:(err)=>{

                    console.error(err);

                }

            });

    }

}