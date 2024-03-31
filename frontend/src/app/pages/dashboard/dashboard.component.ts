import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { NavComponent } from '../../shared/nav/nav.component';
import { AuthService } from '../../services/auth/auth.service';
import { User } from '../../utils/user';
import { PersonalDetailsComponent } from '../../components/personal-details/personal-details.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavComponent, PersonalDetailsComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy{
  userLoginOn: boolean = false;
  userData?: User;
  authService = inject(AuthService);
  
  ngOnInit(): void {
    this.authService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        this.userLoginOn = userLoginOn;
      } 
    })

    this.authService.currentUserData.subscribe({
      next: (userData) => {
        this.userData = userData;
      }
    })
  }
  ngOnDestroy(): void {
    this.authService.currentUserData.unsubscribe();
    this.authService.currentUserLoginOn.unsubscribe();
  }
}
