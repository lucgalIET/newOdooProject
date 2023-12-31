import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
/*Componenti importati */
import { MieiProgettiComponent } from './componenti/miei-progetti/miei-progetti.component';
import { AssignmentsComponent } from './componenti/assignments/assignments.component';
import { MieAttivitaComponent } from './componenti/mie-attivita/mie-attivita.component';
import { AssignmentsProgettoComponent } from './componenti/allAboutAssignmentProg/assignments-progetto/assignments-progetto.component';
import { MieAttivitaImportaComponent } from './componenti/mie-attivita-importa/mie-attivita-importa.component';
import { AssignProgUserComponent } from './componenti/assign-prog-user/assign-prog-user.component';
import { AssignProgMessageComponent } from './componenti/allAboutAssignmentProg/assign-prog-message/assign-prog-message.component';
import { AssignProgNoteComponent } from './componenti/allAboutAssignmentProg/assign-prog-note/assign-prog-note.component';
import { AssignProgActivityComponent } from './componenti/allAboutAssignmentProg/assign-prog-activity/assign-prog-activity.component';
import { AuthGuard } from './auth/keycloak.guard';

import { AdminProjectsComponent } from './componenti/admin/admin-projects/admin-projects.component';
import { AdminProjectCreateComponent } from './componenti/admin/admin-project-create/admin-project-create.component';

import {ProgettiComponent} from './componenti/progetti/progetti.component';


/*Routing per la navigazione tra le pagine*/
const routes: Routes = [
  {path: "", component: MieiProgettiComponent},
  {path: "assignments", component: AssignmentsComponent},
  {path: "user", component: AssignProgUserComponent},
  {path: "progetto", component: AssignmentsProgettoComponent, children:[
    {path: "message", component: AssignProgMessageComponent},
    {path: "note", component: AssignProgNoteComponent},
    {path: "activity", component: AssignProgActivityComponent},
  ]},
  {path: "mieattivita", component: MieAttivitaComponent},
  {path: "importa", component: MieAttivitaImportaComponent},


  {path: "progetti", component: AdminProjectsComponent},
  {path: "crea", component: AdminProjectCreateComponent},

  {path: "progetti", component: ProgettiComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [AuthGuard],
  exports: [RouterModule]
})
export class AppRoutingModule { }
