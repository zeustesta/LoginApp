import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { NavComponent } from '../../shared/nav/nav.component';
import { LoginService } from '../../services/auth/login.service';
import { User } from '../../utils/user';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy{
  userLoginOn: boolean = false;
  userData?: User;
  loginService = inject(LoginService);
  
  ngOnInit(): void {
    this.loginService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        this.userLoginOn = userLoginOn;
      } 
    })

    this.loginService.currentUserData.subscribe({
      next: (userData) => {
        this.userData = userData;
      }
    })
  }
  ngOnDestroy(): void {
    this.loginService.currentUserData.unsubscribe();
    this.loginService.currentUserLoginOn.unsubscribe();
  }
}
