import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assignments-progetto',
  templateUrl: './assignments-progetto.component.html',
  styleUrls: ['./assignments-progetto.component.css']
})
export class AssignmentsProgettoComponent {

  activity = false;

  constructor(private router: Router){};

  open(){
    if (this.activity == false) {
      this.activity = true;
    } else if (this.activity == true) {
      this.activity = false;
    }
  }

  close(){
    this.router.navigateByUrl('/progetto');
    this.activity = false;
  }
}
