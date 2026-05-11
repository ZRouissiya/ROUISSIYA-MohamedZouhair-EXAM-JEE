import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaiementService } from '../../services/paiement.service';

@Component({
  selector: 'app-paiements',
  templateUrl: './paiements.component.html'
})
export class PaiementsComponent implements OnInit {
  contratId!: number;
  paiements: any[] = [];
  totalPaye = 0;
  resteAPayer = 0;
  payload: any = {
    date: new Date().toISOString().slice(0, 10),
    montant: 0,
    type: 'MENSUALITE'
  };

  constructor(private route: ActivatedRoute, private paiementService: PaiementService) {}

  ngOnInit(): void {
    this.contratId = Number(this.route.snapshot.paramMap.get('id'));
    this.load();
  }

  add(): void {
    this.paiementService.addToContrat(this.contratId, this.payload).subscribe(() => this.load());
  }

  private load(): void {
    this.paiementService.getByContrat(this.contratId).subscribe(data => this.paiements = data);
    this.paiementService.totalPaye(this.contratId).subscribe(data => this.totalPaye = data.totalPaye || 0);
    this.paiementService.resteAPayer(this.contratId).subscribe(data => this.resteAPayer = data.resteAPayer || 0);
  }
}