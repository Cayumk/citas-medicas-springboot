package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @Column(name = "id_Especialidad")
    private Long idEspecialidad;

    @Column(name = "Nombre")
    private String nombre;

    public Especialidad() {
    }

	public Especialidad(Long idEspecialidad, String nombre) {
		super();
		this.idEspecialidad = idEspecialidad;
		this.nombre = nombre;
	}

	public Long getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

   
}
