import { Routes } from '@angular/router';
import { UploadComponent } from './componentes/upload/upload.component';
import { DashboardComponent } from './componentes/dashboard/dashboard.component';
import { LoginComponent } from './componentes/login/login.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'upload', component: UploadComponent },
  { path: 'dashboard', component: DashboardComponent },
];

