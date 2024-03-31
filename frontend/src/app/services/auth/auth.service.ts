import { Injectable } from '@angular/core';
import { LoginRequest } from '../../utils/loginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../../environment/environment';
import { Observable, catchError, throwError, BehaviorSubject, tap } from 'rxjs';
import { User } from '../../utils/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl: string = environment.urlHost; 
  private pathUrl: string = '/client';
  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<User> = new BehaviorSubject<User>({id: '0'});

  constructor(private http: HttpClient) {
  }

  login(credentials: LoginRequest): Observable<User> {
    return this.http.get<User>("././assets/data.json").pipe(
      tap(userData => {
        this.currentUserData.next(userData);
        this.currentUserLoginOn.next(true)
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error("Se ha producido un error: " + error.error);
    } else {
      console.error("Codigo de estado retornado: " + error.status + " Error: " + error.message);
    }
    return throwError(() => new Error("Algo salio mal"));
  }

  get userData(): Observable<User> {
    return this.currentUserData.asObservable();
  }

  get userLoginOn(): Observable<boolean>{
    return this.currentUserLoginOn.asObservable();
  }
}
