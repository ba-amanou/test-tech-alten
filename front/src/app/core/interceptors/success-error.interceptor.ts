import { HttpInterceptorFn, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, tap, throwError } from 'rxjs';
import { NotificationService } from '../services/notification.service';

export const successErrorInterceptor: HttpInterceptorFn = (req, next) => {
  const notify = inject(NotificationService);
  return next(req).pipe(
    tap(event => {
      if (event instanceof HttpResponse && ['POST', 'PATCH', 'DELETE'].includes(req.method)) {
        notify.showSuccess('Succès', 'Opération réussie');
      }
    }),
    catchError((error: HttpErrorResponse) => {
      notify.showError('Erreur', error.error.message || 'Une erreur est survenue');
      return throwError(() => error);
    })
  );
};
