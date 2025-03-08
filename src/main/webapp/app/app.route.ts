import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'activate-account',
    loadComponent: () => import('./account/activate-account/activate-account.component').then(m => m.ActivateAccountComponent),
  },
  {
    path: 'account-activated',
    loadComponent: () => import('./account/account-activated/account-activated.component').then(m => m.AccountActivatedComponent),
  },

  {
    path: '',
    loadComponent: () => import('./login/login.component'),
  },
  // jhipster-needle-angular-route
];
