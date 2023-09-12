import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MieiProgettiComponent } from './componenti/miei-progetti/miei-progetti.component';
import { AssignmentsComponent } from './componenti/assignments/assignments.component';
import { MieAttivitaComponent } from './componenti/mie-attivita/mie-attivita.component';
import { AssignmentsProgettoComponent } from './componenti/assignments-progetto/assignments-progetto.component';
import { MieAttivitaImportaComponent } from './componenti/mie-attivita-importa/mie-attivita-importa.component';
import { AssignProgUserComponent } from './componenti/assign-prog-user/assign-prog-user.component';

const routes: Routes = [
  {path: "", component: MieiProgettiComponent},
  {path: "assignments", component: AssignmentsComponent},
  {path: "user", component: AssignProgUserComponent},
  {path: "progetto", component: AssignmentsProgettoComponent},
  {path: "mieattivita", component: MieAttivitaComponent},
  {path: "importa", component: MieAttivitaImportaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
