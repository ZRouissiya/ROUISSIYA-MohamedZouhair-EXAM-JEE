import { Component, OnInit } from '@angular/core';
import { ContratService } from '../../services/contrat.service';

@Component({
  selector: 'app-contrats',
  templateUrl: './contrats.component.html'
})
export class ContratsComponent implements OnInit {
  contrats: any[] = [];

  constructor(private contratService: ContratService) {}

  ngOnInit(): void {
    this.contratService.getAll().subscribe(data => this.contrats = data);
  }

  valider(id: number): void {
    this.contratService.valider(id).subscribe(() => this.refresh());
  }

  resilier(id: number): void {
    this.contratService.resilier(id).subscribe(() => this.refresh());
  }

  private refresh(): void {
    this.contratService.getAll().subscribe(data => this.contrats = data);
  }
}