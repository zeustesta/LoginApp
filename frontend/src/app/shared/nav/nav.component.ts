import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit, OnDestroy{
  userLoginOn: boolean = false;
  authService = inject(AuthService);

  constructor() {
  }

  ngOnInit(): void {
    this.authService.currentUserLoginOn.subscribe({
      next: (userLoginOn) => {
        this.userLoginOn = userLoginOn;
      } 
    })
  }

  ngOnDestroy(): void {
      this.authService.currentUserLoginOn.unsubscribe();
  }
}
