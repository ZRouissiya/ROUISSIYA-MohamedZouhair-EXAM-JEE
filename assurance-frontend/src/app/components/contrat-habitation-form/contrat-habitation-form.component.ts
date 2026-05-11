import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ContratService } from '../../services/contrat.service';

@Component({
  selector: 'app-contrat-habitation-form',
  templateUrl: './contrat-habitation-form.component.html'
})
export class ContratHabitationFormComponent {
  payload: any = {
    dateSouscription: new Date().toISOString().slice(0, 10),
    montantCotisation: 0,
    dureeContrat: 12,
    tauxCouverture: 50,
    clientId: null,
    typeLogement: 'APPARTEMENT',
    adresseLogement: '',
    superficie: 0
  };

  constructor(private contratService: ContratService, private router: Router) {}

  save(): void {
    this.contratService.createHabitation(this.payload).subscribe(() => this.router.navigate(['/contrats']));
  }
}