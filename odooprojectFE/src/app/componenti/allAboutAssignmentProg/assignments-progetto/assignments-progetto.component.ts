import { Component } from '@angular/core';

@Component({
  selector: 'app-assignments-progetto',
  templateUrl: './assignments-progetto.component.html',
  styleUrls: ['./assignments-progetto.component.css']
})
export class AssignmentsProgettoComponent {

  activity = false;

  open(){
    if (this.activity == false) {
      this.activity = true;
    } else {
      this.activity = false;
    }
  }
}
