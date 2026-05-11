import { Component, OnInit } from '@angular/core';
import { Client } from '../../models/client.model';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  search = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void { this.load(); }

  load(): void {
    this.clientService.getAll().subscribe(data => this.clients = data);
  }

  doSearch(): void {
    if (!this.search.trim()) {
      this.load();
      return;
    }
    this.clientService.searchByNom(this.search).subscribe(data => this.clients = data);
  }

  delete(id?: number): void {
    if (!id) return;
    this.clientService.delete(id).subscribe(() => this.load());
  }
}