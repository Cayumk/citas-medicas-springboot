import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'

})

export class CitasService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  obtenerEspecialidades(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/especialidades`);
  }

  obtenerEPS(): Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/eps/obtener`);
  }

  obtenerMedicosPorEspecialidad(especialidad: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/medicos/por-especialidad`, {
      params: new HttpParams().set('especialidad', especialidad)
    });
  }

  verificarDisponibilidad(idMedico: number, fecha: string): Observable<any> {
    const params = new HttpParams()
      .set('idMedico', idMedico)
      .set('fecha', fecha);

    return this.http.get(`${this.apiUrl}/citas/verificar-disponibilidad`, { params });
  }

  agendarCita(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/citas`, data);
  }
}
