import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LoginService } from '../../services/auth/login.service';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit {
  userLoginOn: boolean = false;
  loginService = inject(LoginService);

  constructor() {
  }

  ngOnInit(): void {
    this.loginService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        this.userLoginOn = userLoginOn;
      } 
    })
  }

  logOut() {
    this.loginService.logout();
  }
}
