import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NgIf } from '@angular/common';

import { NavbarComponent } from './shared/navbar/navbar';
import { Footer } from './shared/footer/footer';
import { AuthService } from './auth/auth';


import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    NavbarComponent,
    Footer,
    NgIf,

  ],
  template: `
    <ng-container *ngIf="auth.estaAutenticado(); else soloLogin">
    <router-outlet></router-outlet>
      <app-footer></app-footer>
    </ng-container>

    <ng-template #soloLogin>
      <router-outlet></router-outlet>
    </ng-template>
  `
})
export class App {
  
  mostrarLoginAuxiliar = false; 

  constructor(public auth: AuthService, private router:Router) {}

  abrirModalAuxiliar() {
    this.mostrarLoginAuxiliar = true;
  }

  cerrarModalAuxiliar() {
    this.mostrarLoginAuxiliar = false;
  }
}

