import { Component, OnInit, inject } from '@angular/core';
import { NavComponent } from '../../shared/nav/nav.component';
import { LoginService } from '../../services/auth/login.service';
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
  loginService = inject(LoginService);
  
  ngOnInit(): void {
    this.loginService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        this.userLoginOn = userLoginOn;
      } 
    })
  }
}
