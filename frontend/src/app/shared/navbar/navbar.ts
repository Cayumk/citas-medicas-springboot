import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css']
})
export class NavbarComponent implements OnInit {
  @Output() abrirModal = new EventEmitter<void>();

  usuarioNombre: string | null = null;
  rol: string | null = null;

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    const usuario = this.auth.getUsuario();
    if (usuario && usuario.rol) {
      this.usuarioNombre = usuario.nombre;
      this.rol = usuario.rol.trim().toLowerCase();
    }
  }

  cerrarSesion(): void {
    this.auth.logout();
    this.usuarioNombre = null;
    this.rol = null;
    this.router.navigate(['/login']);
  }

  activarModalAuxiliar(): void {
    this.abrirModal.emit();
  }
}




