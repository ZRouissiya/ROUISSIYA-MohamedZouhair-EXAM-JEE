import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  payload = { username: '', password: '' };
  error = '';

  constructor(private authService: AuthService, private router: Router) {}

  submit(): void {
    this.error = '';
    this.authService.login(this.payload).subscribe({
      next: () => this.router.navigate(['/']),
      error: () => this.error = 'Identifiants invalides'
    });
  }
}