import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuthService } from '../auth'; 
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, HttpClientModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  correo: string = '';
  contrasena: string = '';
  mensaje: string = '';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {}

  login(): void {
    const body = {
      correo: this.correo,
      contrasena: this.contrasena
    };

    // 🔹 Intento 1: iniciar sesión como usuario
    this.http.post<any>('http://localhost:8080/usuarios/login', body).subscribe({
      next: usuario => {
        usuario.rol = 'usuario'; 
        this.auth.login(usuario);
        localStorage.setItem('usuario', JSON.stringify(usuario));

        Swal.fire({
          icon: 'success',
          title: '¡Bienvenido!',
          text: 'Inicio de sesión exitoso.',
          confirmButtonText: 'Continuar',
          confirmButtonColor: '#3085d6'
        }).then(() => {
          location.href = '/solicitar-cita';
        });
      },
      error: () => {
     
        const auxBody = {
          nombre: this.correo,
          contrasena: this.contrasena
        };

        this.http.post<any>('http://localhost:8080/auxiliares/login', auxBody).subscribe({
          next: auxiliar => {
            auxiliar.rol = 'auxiliar';
            this.auth.login(auxiliar);
            localStorage.setItem('usuario', JSON.stringify(auxiliar));
            localStorage.setItem('auxiliarId', auxiliar.id); // Opcional, por si lo necesitas

            Swal.fire({
              icon: 'success',
              title: '¡Bienvenido auxiliar!',
              text: 'Inicio de sesión exitoso.',
              confirmButtonText: 'Continuar',
              confirmButtonColor: '#3085d6'
            }).then(() => {
              location.href = '/asignar-citas';
            });
          },
          error: () => {
            this.mensaje = 'Credenciales incorrectas';
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Credenciales incorrectas',
              confirmButtonText: 'Intentar de nuevo',
              confirmButtonColor: '#d33'
            });
          }
        });
      }
    });
  }
}
