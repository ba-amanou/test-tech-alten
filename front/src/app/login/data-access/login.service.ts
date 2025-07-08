import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly path = `${environment.apiUrl}/token`;
  private readonly http = inject(HttpClient);

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.path, { email, password }).pipe(
        tap( response => {
            this.storeToken(response.token);
            this.storeEmail(email);
        })
    );
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  isAdmin(): boolean {
    return this.getEmail() === 'admin@admin.com';
  }

  storeToken(token: string): void {
    localStorage.setItem('jwt', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  storeEmail(email: string): void {
    localStorage.setItem('email', email);
  }

  getEmail(): string | null {
    return localStorage.getItem('email');
  }  

  logout(): void {
    localStorage.removeItem('jwt');
  }
}
