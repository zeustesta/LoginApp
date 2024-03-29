import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../../utils/user';
import { environment } from '../../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl: String = environment.urlApi + '/client';

  constructor(private http: HttpClient) {}

  getUser (id: String): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error("Se ha producido un error: " + error);
    } else {
      console.error("Error: " + error);
    }
    return throwError(() => new Error("Algo salio mal"));
  }
}
