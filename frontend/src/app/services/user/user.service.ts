import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, of, throwError } from 'rxjs';
import { User } from '../../utils/user';
import { environment } from '../../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl: String = environment.urlApi + '/client';
  private currentId: String | null = "";

  constructor(private http: HttpClient) {}

  getUser(): Observable<User> {
    if (this.currentId == "") {
      return of({ id: '', firstName: '', lastName: '', email: '' });
    } else {
      return this.http.get<User>(`${this.baseUrl}/${this.currentId}`).pipe(
        catchError(this.handleError)
      );
    }
  }

  updateUser(update: User): Observable<String> {
    update.id = this.currentId!.toString();

    return this.http.put<String>(`${this.baseUrl}/update`, update).pipe(
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

  set userId(userId: String) {
    this.currentId = userId;
  }
}
