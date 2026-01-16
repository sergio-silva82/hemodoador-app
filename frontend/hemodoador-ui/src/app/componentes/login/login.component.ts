import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username = '';
  password = '';
  erro = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() { 
    this.auth.login(this.username, this.password).subscribe({ 
      next: (res) => { localStorage.setItem('token', res.token); // ✅ guarda o token 
      this.router.navigate(['/upload']); }, 
      error: () => this.erro = 'Usuário ou senha inválidos' }); 
  }
}