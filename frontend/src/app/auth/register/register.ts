import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
  imports: [CommonModule, FormsModule]
})
export class RegisterComponent implements OnInit {
  usuario = {
    idUsuario: null,
    nombre: '',
    apellidos: '',
    telefono: '',
    direccion: '',
    correoElectronico: '',
    rh: '',
    edad: null,
    contrasena: '',
    rol: 'paciente',
    eps: { idEps: null }
  };

  mensaje = '';
  epsList: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.cargarEps();
  }

  cargarEps() {
    this.http.get<any[]>('http://localhost:8080/eps/obtener').subscribe({
      next: data => this.epsList = data,
      error: err => console.error('Error al cargar EPS', err)
    });
  }

  registrar() {
    const campos = this.usuario;
    const epsSeleccionada = this.usuario.eps?.idEps;

    if (!campos.idUsuario || !campos.nombre || !campos.apellidos || !campos.telefono ||
        !campos.direccion || !campos.correoElectronico || !campos.rh ||
        !campos.edad || !campos.contrasena || !epsSeleccionada) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos incompletos',
        text: 'Por favor, completa todos los campos.',
        confirmButtonColor: '#f0ad4e'
      });
      return;
    }

    this.http.post('http://localhost:8080/usuarios/registrar', this.usuario).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Registro exitoso',
          text: 'Usuario registrado correctamente.',
          confirmButtonColor: '#198754'
        }).then(() => {
          this.router.navigate(['/login']);
        });
      },
      error: err => {
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Hubo un problema al registrar el usuario.',
          confirmButtonColor: '#d33'
        });
      }
    });
  }

  irALogin() {
    this.router.navigate(['/login']);
  }
}
