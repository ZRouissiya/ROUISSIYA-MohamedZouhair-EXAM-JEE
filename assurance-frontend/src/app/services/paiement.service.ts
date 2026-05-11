import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Paiement } from '../models/paiement.model';

@Injectable({ providedIn: 'root' })
export class PaiementService {
  private readonly baseUrl = `${environment.apiUrl}/paiements`;

  constructor(private http: HttpClient) {}

  addToContrat(contratId: number, payload: Paiement): Observable<Paiement> {
    return this.http.post<Paiement>(`${this.baseUrl}/contrat/${contratId}`, payload);
  }

  getByContrat(contratId: number): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(`${this.baseUrl}/contrat/${contratId}`);
  }

  totalPaye(contratId: number): Observable<{ totalPaye: number }> {
    return this.http.get<{ totalPaye: number }>(`${this.baseUrl}/contrat/${contratId}/total`);
  }

  resteAPayer(contratId: number): Observable<{ resteAPayer: number }> {
    return this.http.get<{ resteAPayer: number }>(`${this.baseUrl}/contrat/${contratId}/reste`);
  }
}