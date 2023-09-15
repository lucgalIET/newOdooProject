import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [AuthGuard],
  exports: [RouterModule]
})
export class AppRoutingModule { }
