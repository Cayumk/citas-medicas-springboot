import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioLogueado: any = null;

  login(usuario: any) {
    this.usuarioLogueado = usuario;
    localStorage.setItem('usuario', JSON.stringify(usuario));
  }

  loginAuxiliar() {
    localStorage.setItem('auxiliarAutenticado', 'true');
  }

  logout() {
    this.usuarioLogueado = null;
    localStorage.removeItem('usuario');
    localStorage.removeItem('auxiliarAutenticado'); // 🔹 También cerrar sesión del auxiliar
  }

  getUsuario() {
    if (!this.usuarioLogueado) {
      const guardado = localStorage.getItem('usuario');
      if (guardado) {
        this.usuarioLogueado = JSON.parse(guardado);
      }
    }
    return this.usuarioLogueado;
  }

  estaAutenticado(): boolean {
    return !!this.getUsuario();
  }

  esAdmin(): boolean {
    const user = this.getUsuario();
    return user && user.rol === 'admin';
  }

  esAuxiliar(): boolean {
    return localStorage.getItem('auxiliarAutenticado') === 'true';
  }
}
