import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContratService } from '../../services/contrat.service';

@Component({
  selector: 'app-contrat-details',
  templateUrl: './contrat-details.component.html'
})
export class ContratDetailsComponent implements OnInit {
  contrat: any;

  constructor(private route: ActivatedRoute, private contratService: ContratService) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.contratService.getById(id).subscribe(data => this.contrat = data);
  }
}