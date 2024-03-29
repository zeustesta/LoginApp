import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from './login.service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const loginService = inject(LoginService);
  let token = loginService.userToken;

  if (token != '') {
    req = req.clone(
      {
        setHeaders: {
          'Content-Type': 'application/json;charset=utf-8',
          'Accept': 'application/json',
          'Authorization': `Bearer ${token}`
        }
      }
    )
  }
  return next(req);
}