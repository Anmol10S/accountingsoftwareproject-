import { Component, Inject } from '@angular/core';

import {
MAT_DIALOG_DATA,
MatDialogModule,
MatDialogRef
} from '@angular/material/dialog';

import { MatButtonModule } from '@angular/material/button';

@Component({

selector:'app-delete-dialog',

standalone:true,

imports:[
MatDialogModule,
MatButtonModule
],

templateUrl:'./delete-dialog.component.html'

})

export class DeleteDialogComponent{

constructor(

private dialogRef:MatDialogRef<DeleteDialogComponent>,

@Inject(MAT_DIALOG_DATA)
public data:any

){}

confirm(){

this.dialogRef.close(true);

}

}