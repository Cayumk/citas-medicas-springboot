import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./layout/layout').then(m => m.Layout),
    children: [
      { path: '', redirectTo: 'solicitar-cita', pathMatch: 'full' },
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
      },
    ],
  },
  { path: '**', redirectTo: '' }
];


