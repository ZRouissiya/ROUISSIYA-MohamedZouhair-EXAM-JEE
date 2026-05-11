import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ContratService } from '../../services/contrat.service';

@Component({
  selector: 'app-contrat-automobile-form',
  templateUrl: './contrat-automobile-form.component.html'
})
export class ContratAutomobileFormComponent {
  payload: any = {
    dateSouscription: new Date().toISOString().slice(0, 10),
    montantCotisation: 0,
    dureeContrat: 12,
    tauxCouverture: 50,
    clientId: null,
    numeroImmatriculation: '',
    marqueVehicule: '',
    modeleVehicule: ''
  };

  constructor(private contratService: ContratService, private router: Router) {}

  save(): void {
    this.contratService.createAutomobile(this.payload).subscribe(() => this.router.navigate(['/contrats']));
  }
}