import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ContratAssurance } from '../models/contrat.model';

@Injectable({ providedIn: 'root' })
export class ContratService {
  private readonly baseUrl = `${environment.apiUrl}/contrats`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<ContratAssurance[]> { return this.http.get<ContratAssurance[]>(this.baseUrl); }
  getById(id: number): Observable<ContratAssurance> { return this.http.get<ContratAssurance>(`${this.baseUrl}/${id}`); }
  getByClient(clientId: number): Observable<ContratAssurance[]> { return this.http.get<ContratAssurance[]>(`${this.baseUrl}/client/${clientId}`); }

  createAutomobile(payload: any): Observable<any> { return this.http.post(`${this.baseUrl}/automobile`, payload); }
  createHabitation(payload: any): Observable<any> { return this.http.post(`${this.baseUrl}/habitation`, payload); }
  createSante(payload: any): Observable<any> { return this.http.post(`${this.baseUrl}/sante`, payload); }

  valider(id: number): Observable<ContratAssurance> { return this.http.put<ContratAssurance>(`${this.baseUrl}/${id}/valider`, {}); }
  resilier(id: number): Observable<ContratAssurance> { return this.http.put<ContratAssurance>(`${this.baseUrl}/${id}/resilier`, {}); }
}