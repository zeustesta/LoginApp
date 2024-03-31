import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { RegisterComponent } from './auth/register/register.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'start', component: DashboardComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'start', pathMatch: 'full' }
];
