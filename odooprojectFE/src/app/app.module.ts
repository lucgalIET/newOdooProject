import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import {MatMenuModule} from '@angular/material/menu';

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
    AssignProgActivityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
