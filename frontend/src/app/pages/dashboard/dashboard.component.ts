import { Component, OnInit, inject } from '@angular/core';
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
export class DashboardComponent implements OnInit {
  userLoginOn: boolean = false;
  authService = inject(AuthService);
  
  ngOnInit(): void {
    this.authService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        this.userLoginOn = userLoginOn;
      } 
    })
  }
}
