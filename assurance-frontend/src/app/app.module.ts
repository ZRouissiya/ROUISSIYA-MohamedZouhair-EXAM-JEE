import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BaseChartDirective, provideCharts, withDefaultRegisterables } from 'ng2-charts';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { ClientsComponent } from './components/clients/clients.component';
import { ClientFormComponent } from './components/client-form/client-form.component';
import { ContratsComponent } from './components/contrats/contrats.component';
import { ContratDetailsComponent } from './components/contrat-details/contrat-details.component';
import { ContratAutomobileFormComponent } from './components/contrat-automobile-form/contrat-automobile-form.component';
import { ContratHabitationFormComponent } from './components/contrat-habitation-form/contrat-habitation-form.component';
import { ContratSanteFormComponent } from './components/contrat-sante-form/contrat-sante-form.component';
import { PaiementsComponent } from './components/paiements/paiements.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    ClientsComponent,
    ClientFormComponent,
    ContratsComponent,
    ContratDetailsComponent,
    ContratAutomobileFormComponent,
    ContratHabitationFormComponent,
    ContratSanteFormComponent,
    PaiementsComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    BaseChartDirective,
    AppRoutingModule
  ],
  providers: [
    provideCharts(withDefaultRegisterables()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}