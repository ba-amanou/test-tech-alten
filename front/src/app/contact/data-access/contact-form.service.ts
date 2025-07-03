import { inject, Injectable } from '@angular/core';
import { ContactForm } from './contact-form.model';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactFormService {

  private readonly http = inject(HttpClient);
  private readonly path = "/api/contact"

  public sendContactForm(form : ContactForm) : Observable<boolean>{
    return this.http.post<boolean>(this.path, form).pipe(
      catchError((error) => {
        return of (true);
      })
    );
  }
}
