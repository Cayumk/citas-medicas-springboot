import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CitasService } from '../citas.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-solicitar',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './solicitar.html',
  styleUrls: ['./solicitar.css']
})
export class SolicitarComponent implements OnInit {

  disponibilidadForm!: FormGroup;
  citaForm!: FormGroup;

  especialidades: string[] = [];
  medicos: any[] = [];
  medicosFiltrados: any[] = [];
  epsList: any[] = [];

  mensaje: string = '';
  disponible = false;

  constructor(private fb: FormBuilder, private citasService: CitasService) {}

  ngOnInit(): void {
      this.disponibilidadForm = this.fb.group({
        especialidad: ['', Validators.required],
        idMedico: ['', Validators.required],
        fecha: ['', Validators.required]
      });
  
      this.citaForm = this.fb.group({
        nombreUsuario: ['', Validators.required],
        identificacionUsuario: ['', Validators.required],
        epsUsuario: ['', Validators.required]
      });

      this.cargarEspecialidades();
      this.cargarEPS();
    }

    cargarEspecialidades(){
      this.citasService.obtenerEspecialidades().subscribe(data => {
        this.especialidades = data;
      });
    }

    cargarEPS(){
      this.citasService.obtenerEPS().subscribe(data => {
        this.epsList = data;
      });
    }

    onEspecialidadChange() {
      const especialidad = this.disponibilidadForm.get('especialidad')?.value;
      this.citasService.obtenerMedicosPorEspecialidad(especialidad).subscribe(data => {
        this.medicosFiltrados = data;
      });
    }

    verificarDisponibilidad() {
      const { idMedico, fecha } = this.disponibilidadForm.value;

      if(!idMedico || !fecha) {
        this.disponible = false;
        this.mensaje = "Por favor seleccione un medico y una fecha.";
        return;
      }

      this.citasService.verificarDisponibilidad(idMedico, fecha).subscribe({
        next: (respuesta) => {
          if (respuesta.disponible) {
            this.disponible = true;
            this.mensaje = "Cita disponible. Por favor complete sus datos.";
          } else {
            this.disponible = false;
            this.mensaje = "No hay disponibilidad para ese médico en esa fecha.";
          }
        },
        error: err => {
          this.disponible = false;
          this.mensaje = "Error al verificar disponibilidad. Intentelo de nuevo.";
        }
      });
    }

    solicitarCita() {
      const citaCompleta = {
        ...this.disponibilidadForm.value,
        ...this.citaForm.value
      };

      this.citasService.agendarCita(citaCompleta).subscribe({
        next: () => {
          this.mensaje = 'Cita agendada correctamente.';
          this.disponibilidadForm.reset();
          this.citaForm.reset();
          this.disponible = false;
        },
        error: err => {
          this.mensaje = 'Error al agendar la cita: ' + err.error?.mensaje || err.message;
        }
      });
    }
  
}
