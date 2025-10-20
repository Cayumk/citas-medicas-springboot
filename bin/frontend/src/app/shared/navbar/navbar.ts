import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css'],
    template: `
    <nav>
      <a routerLink="/solicitar">Solicitar Cita</a>
      <a routerLink="/mis-citas">Mis Citas</a>
      <a routerLink="/asignar-citas">Asignar Citas</a>
    </nav>
  `
})
export class Navbar {

}
