import { Injectable } from '@angular/core';
import { LoginRequest } from '../../utils/loginRequest';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../../environment/environment';
import { Observable, catchError, throwError, BehaviorSubject, tap, map } from 'rxjs';
import { User } from '../../utils/user';
import { RegisterRequest } from '../../utils/registerRequest';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl: string = `${environment.urlHost}/auth`; 
  currentUserLoginOn = new BehaviorSubject<boolean>(false);
  currentUserToken = new BehaviorSubject<String>("");

  constructor(private http: HttpClient, private userService: UserService) {
    this.getStorage();
  }

  getStorage() {
    this.currentUserLoginOn = new BehaviorSubject<boolean>((sessionStorage.getItem('token') != null));
    this.currentUserToken = new BehaviorSubject<String>(sessionStorage.getItem('token') || "");
  }

  login(credentials: LoginRequest): Observable<User> {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
      tap(userData => {
        sessionStorage.setItem('token', userData);
        this.currentUserToken.next(userData.token);
        this.currentUserLoginOn.next(true);
        this.userService.userId = userData.userId;
      }),
      map((userData) => userData),
      catchError(this.handleError)
    );
  }

  logout(): void {
    sessionStorage.removeItem('token');
    this.currentUserLoginOn.next(false);
  }

  register(newUser: RegisterRequest): Observable<String> {
    return this.http.post<String>(`${this.apiUrl}/register`, newUser).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error("Se ha producido un error: " + error.error);
    } else {
      console.error("Codigo de estado retornado: " + error.status + " Error: " + error.message);
    }
    return throwError(() => new Error("Algo salio mal: " + error.message));
  }

  get userLoginOn(): Observable<boolean>{
    return this.currentUserLoginOn.asObservable();
  }

  get userToken(): String {
    return this.currentUserToken.value;
  }
}
