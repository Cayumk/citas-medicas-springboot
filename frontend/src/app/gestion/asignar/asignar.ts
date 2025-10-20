import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-asignar',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './Asignar.html',
  styleUrls: ['./asignar.css']
})
export class AsignarComponent implements OnInit {
  citasPendientes: any[] = [];
  medicos: any[] = [];
  rol: string = '';
  nombreUsuario: string = '';

  alerta = {
    visible: false,
    tipo: 'success', // 'success' | 'danger'
    mensaje: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    const usuarioStr = localStorage.getItem('usuario');

    if (!usuarioStr) {
      alert('Debes iniciar sesión');
      this.router.navigate(['/login']);
      return;
    }

    const usuario = JSON.parse(usuarioStr);
    this.rol = usuario.rol?.toLowerCase();
    this.nombreUsuario = usuario.nombre?.toLowerCase();

    this.obtenerCitas();

    if (this.rol === 'auxiliar') {
      this.obtenerMedicos();
    }
  }

  obtenerCitas(): void {
    this.http.get<any[]>('http://localhost:8080/citas/obtener')
      .subscribe((citas) => {
        this.citasPendientes = citas
          .filter(c => c.estado === 'pendiente')
          .filter(c => this.rol === 'auxiliar' || c.nombreUsuario?.toLowerCase() === this.nombreUsuario)
          .map(c => ({
            ...c,
            medicoSeleccionado: '',
            nuevaFecha: ''
          }));
      });
  }

  obtenerMedicos(): void {
    this.http.get<any[]>('http://localhost:8080/medicos')
      .subscribe(data => this.medicos = data);
  }

  asignarCita(idCita: number, idMedico: string, fecha: string): void {
    if (!idMedico || !fecha) {
      this.mostrarAlerta('danger', 'Debe seleccionar un médico y una fecha.');
      return;
    }

    this.http.put<any>(`http://localhost:8080/citas/asignar/${idCita}`, {
      id_medico: idMedico,
      fecha: fecha
    }).subscribe({
      next: (res: any) => {
        const mensaje = res?.mensaje || 'Cita asignada con éxito.';
        this.mostrarAlerta('success', mensaje);
        this.obtenerCitas(); // Recargar citas
      },
      error: (err) => {
        let mensaje = 'Error al asignar cita';
        if (typeof err.error === 'string') {
          mensaje = err.error;
        } else if (err.error?.mensaje) {
          mensaje = err.error.mensaje;
        }
        this.mostrarAlerta('danger', mensaje);
      }
    });
  }

  mostrarAlerta(tipo: 'success' | 'danger', mensaje: string): void {
    this.alerta = {
      visible: true,
      tipo,
      mensaje
    };
    setTimeout(() => this.alerta.visible = false, 4000);
  }
}
