import { Component } from '@angular/core';
import { ProjectDialogBodyComponent } from 'src/app/project-dialog-body/project-dialog-body.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-progetti',
  templateUrl: './progetti.component.html',
  styleUrls: ['./progetti.component.css']
})
export class ProgettiComponent {
  constructor(private matDialog:MatDialog){

  }
  openProjectDialog(){
    console.log("Mi hai cliccato!");
    this.matDialog.open(ProjectDialogBodyComponent,{
      width: '350px',

    })
  }
}
