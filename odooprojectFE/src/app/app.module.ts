import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import {MatMenuModule} from '@angular/material/menu';

import {CdkDrag, CdkDragPlaceholder, CdkDropList, CdkDropListGroup} from '@angular/cdk/drag-drop';

import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './componenti/navbar/navbar.component';
import { MieAttivitaImportaComponent } from './componenti/mie-attivita-importa/mie-attivita-importa.component';
import { MieAttivitaComponent } from './componenti/mie-attivita/mie-attivita.component';
import { MieiProgettiComponent } from './componenti/miei-progetti/miei-progetti.component';
import { AssignmentsComponent } from './componenti/assignments/assignments.component';
import { AssignmentsProgettoComponent } from './componenti/allAboutAssignmentProg/assignments-progetto/assignments-progetto.component';
import { AssignProgUserComponent } from './componenti/assign-prog-user/assign-prog-user.component';
import { AssignProgMessageComponent } from './componenti/allAboutAssignmentProg/assign-prog-message/assign-prog-message.component';
import { AssignProgNoteComponent } from './componenti/allAboutAssignmentProg/assign-prog-note/assign-prog-note.component';
import { AssignProgActivityComponent } from './componenti/allAboutAssignmentProg/assign-prog-activity/assign-prog-activity.component';

import { AdminNavbarComponent } from './componenti/admin/admin-navbar/admin-navbar.component';
import { AdminProjectsComponent } from './componenti/admin/admin-projects/admin-projects.component';
import { AdminProjectCreateComponent } from './componenti/admin/admin-project-create/admin-project-create.component';
import { AdminProjActivitiesComponent } from './componenti/admin/admin-proj-activities/admin-proj-activities.component';
import { AdminProjActivityDetailsComponent } from './componenti/admin/admin-proj-activity-details/admin-proj-activity-details.component';

import { ProgettiComponent } from './componenti/progetti/progetti.component';
import { ProjectDialogBodyComponent } from './project-dialog-body/project-dialog-body.component';


// function initializeKeycloak(keycloak: KeycloakService) {
//   return () =>
//     keycloak.init({
//       config: {
//         url: 'http://docker.iet.it:8585',
//         realm: 'iet-sso',
//         clientId: 'dudu-app'
//       },
//       initOptions: {
//         onLoad: 'check-sso',
//         silentCheckSsoRedirectUri:
//           window.location.origin + '/assets/silent-check-sso.html'
//       }
//     });
// }

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MieAttivitaImportaComponent,
    MieAttivitaComponent,
    MieiProgettiComponent,
    AssignmentsComponent,
    AssignmentsProgettoComponent,
    AssignProgUserComponent,
    AssignProgMessageComponent,
    AssignProgNoteComponent,
    AssignProgActivityComponent,

    AdminNavbarComponent,
    AdminProjectsComponent,
    AdminProjectCreateComponent,
    ProgettiComponent,
    ProjectDialogBodyComponent,
    AdminProjectCreateComponent,
    AdminProjActivityDetailsComponent,
    AdminProjActivitiesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatMenuModule,
    CdkDrag,
    CdkDragPlaceholder,
    CdkDropList,
    CdkDropListGroup,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatDialogModule,
    CdkDropListGroup
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
