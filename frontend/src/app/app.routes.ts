import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: '',
    loadComponent: () => import('./layout/layout').then(m => m.Layout),
    children: [
      {
        path: 'solicitar-cita',
        loadComponent: () =>
          import('./citas/solicitar/solicitar').then(m => m.SolicitarComponent),
      },
      {
        path: 'mis-citas',
        loadComponent: () =>
          import('./citas/mis-citas/mis-citas').then(m => m.MisCitasComponent),
      },
      {
        path: 'asignar-citas',
        loadComponent: () =>
          import('./gestion/asignar/asignar').then(m => m.AsignarComponent),
      }
    ],
  },


  {
    path: 'login',
    loadComponent: () =>
      import('./auth/login/login').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./auth/register/register').then(m => m.RegisterComponent),
  },

 
  { path: '**', redirectTo: 'login' },

  {
  path: 'auxiliar/home',
  loadComponent: () =>
    import('./auxiliar/auxiliar-home/auxiliar-home').then(m => m.AuxiliarHomeComponent)
  }

];
