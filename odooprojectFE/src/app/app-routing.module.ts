import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MieiProgettiComponent } from './componenti/miei-progetti/miei-progetti.component';
import { AssignmentsComponent } from './componenti/assignments/assignments.component';
import { MieAttivitaComponent } from './componenti/mie-attivita/mie-attivita.component';

const routes: Routes = [
  {path: "", component: MieiProgettiComponent},
  {path: "assignments", component: AssignmentsComponent},
  {path: "mieattivita", component: MieAttivitaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
