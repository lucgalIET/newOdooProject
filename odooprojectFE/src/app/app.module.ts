import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './componenti/navbar/navbar.component';
import { MieAttivitaImportaComponent } from './componenti/mie-attivita-importa/mie-attivita-importa.component';
import { MieAttivitaComponent } from './componenti/mie-attivita/mie-attivita.component';
import { MieiProgettiComponent } from './componenti/miei-progetti/miei-progetti.component';
import { AssignmentsComponent } from './componenti/assignments/assignments.component';
import { AssignmentsProgettoComponent } from './componenti/assignments-progetto/assignments-progetto.component';
import { AssignProgUserComponent } from './componenti/assign-prog-user/assign-prog-user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MieAttivitaImportaComponent,
    MieAttivitaComponent,
    MieiProgettiComponent,
    AssignmentsComponent,
    AssignmentsProgettoComponent,
    AssignProgUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
