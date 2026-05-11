import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { LoginRequest, LoginResponse } from '../models/auth.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {}

  login(payload: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, payload).pipe(
      tap(res => {
        localStorage.setItem('token', res.token);
        localStorage.setItem('username', res.username);
      })
    );
  }

  register(payload: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/register`, payload, { responseType: 'text' });
  }

  changePassword(payload: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/change-password`, payload, { responseType: 'text' });
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getUsername(): string {
    return localStorage.getItem('username') || '';
  }
}