import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ClientsComponent } from './components/clients/clients.component';
import { ClientFormComponent } from './components/client-form/client-form.component';
import { ContratsComponent } from './components/contrats/contrats.component';
import { ContratDetailsComponent } from './components/contrat-details/contrat-details.component';
import { ContratAutomobileFormComponent } from './components/contrat-automobile-form/contrat-automobile-form.component';
import { ContratHabitationFormComponent } from './components/contrat-habitation-form/contrat-habitation-form.component';
import { ContratSanteFormComponent } from './components/contrat-sante-form/contrat-sante-form.component';
import { PaiementsComponent } from './components/paiements/paiements.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'clients', component: ClientsComponent, canActivate: [AuthGuard] },
  { path: 'clients/new', component: ClientFormComponent, canActivate: [AuthGuard] },
  { path: 'clients/:id/edit', component: ClientFormComponent, canActivate: [AuthGuard] },
  { path: 'contrats', component: ContratsComponent, canActivate: [AuthGuard] },
  { path: 'contrats/:id', component: ContratDetailsComponent, canActivate: [AuthGuard] },
  { path: 'contrats/automobile/new', component: ContratAutomobileFormComponent, canActivate: [AuthGuard] },
  { path: 'contrats/habitation/new', component: ContratHabitationFormComponent, canActivate: [AuthGuard] },
  { path: 'contrats/sante/new', component: ContratSanteFormComponent, canActivate: [AuthGuard] },
  { path: 'paiements/contrat/:id', component: PaiementsComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}