import { Component, OnInit } from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { DashboardService } from '../../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  totalClients = 0;
  totalContrats = 0;
  totalPaiements = 0;

  contratsTypeChart: ChartConfiguration<'pie'>['data'] = { labels: [], datasets: [{ data: [] }] };
  contratsStatutChart: ChartConfiguration<'bar'>['data'] = { labels: [], datasets: [{ data: [], label: 'Contrats' }] };
  paiementsTypeChart: ChartConfiguration<'pie'>['data'] = { labels: [], datasets: [{ data: [] }] };
  paiementsMensuelChart: ChartConfiguration<'line'>['data'] = { labels: [], datasets: [{ data: [], label: 'Paiements mensuels' }] };

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.statsGlobales().subscribe(stats => {
      this.totalClients = stats.totalClients;
      this.totalContrats = stats.totalContrats;
      this.totalPaiements = stats.totalPaiements;

      this.contratsTypeChart = this.toPieChart(stats.contratsParType);
      this.contratsStatutChart = {
        labels: Object.keys(stats.contratsParStatut || {}),
        datasets: [{ label: 'Contrats par statut', data: Object.values(stats.contratsParStatut || {}) as number[] }]
      };
      this.paiementsTypeChart = this.toPieChart(stats.paiementsParType);
      this.paiementsMensuelChart = {
        labels: Object.keys(stats.statistiquesMensuellesPaiements || {}),
        datasets: [{ label: 'Paiements mensuels', data: Object.values(stats.statistiquesMensuellesPaiements || {}) as number[] }]
      };
    });
  }

  private toPieChart(source: Record<string, number>): ChartConfiguration<'pie'>['data'] {
    const entries = Object.entries(source || {});
    return {
      labels: entries.map(([k]) => k),
      datasets: [{ data: entries.map(([, v]) => v) }]
    };
  }
}