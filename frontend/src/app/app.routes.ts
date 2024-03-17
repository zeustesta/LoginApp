import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'start', component: DashboardComponent },
  { path: '', redirectTo: 'start', pathMatch: 'full' }
];
