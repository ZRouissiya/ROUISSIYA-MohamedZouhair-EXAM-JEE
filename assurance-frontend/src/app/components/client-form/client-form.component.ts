import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html'
})
export class ClientFormComponent implements OnInit {
  client: any = { nom: '', email: '' };
  id?: number;

  constructor(private route: ActivatedRoute, private router: Router, private clientService: ClientService) {}

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get('id');
    if (param) {
      this.id = Number(param);
      this.clientService.getById(this.id).subscribe(c => this.client = c);
    }
  }

  save(): void {
    const obs = this.id
      ? this.clientService.update(this.id, this.client)
      : this.clientService.create(this.client);
    obs.subscribe(() => this.router.navigate(['/clients']));
  }
}