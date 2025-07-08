import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from 'app/login/data-access/login.service';
import { CardModule } from 'primeng/card';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [FormsModule, CardModule, ButtonModule, InputTextModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {

  email: string = '';
  password: string = '';
  
  private readonly loginService = inject(LoginService);
  private readonly router = inject(Router);
 
  ngOnInit() {
    if (this.loginService.isAuthenticated()) {
      this.router.navigate(['/home']);
    }
  }

  onLogin(){
    this.loginService.login(this.email, this.password).subscribe({
      next: () => { this.router.navigate(['/home']); }
    });
  }
}
