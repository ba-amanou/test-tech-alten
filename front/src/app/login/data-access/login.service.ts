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
        })
    );
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  storeToken(token: string): void {
    localStorage.setItem('jwt', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  logout(): void {
    localStorage.removeItem('jwt');
  }
}
