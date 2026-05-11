import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ContratService } from '../../services/contrat.service';

@Component({
  selector: 'app-contrat-sante-form',
  templateUrl: './contrat-sante-form.component.html'
})
export class ContratSanteFormComponent {
  payload: any = {
    dateSouscription: new Date().toISOString().slice(0, 10),
    montantCotisation: 0,
    dureeContrat: 12,
    tauxCouverture: 50,
    clientId: null,
    niveauCouverture: 'BASIQUE',
    nombrePersonnesCouvertes: 1
  };

  constructor(private contratService: ContratService, private router: Router) {}

  save(): void {
    this.contratService.createSante(this.payload).subscribe(() => this.router.navigate(['/contrats']));
  }
}