package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "auxiliar")
public class Auxiliar {

    @Id
    @Column(name = "id_Auxiliar")
    private Long idAuxiliar;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Contrasena")
    private String contrasena;

    public Auxiliar() {
    }

	public Auxiliar(Long idAuxiliar, String nombre, String contrasena) {
		super();
		this.idAuxiliar = idAuxiliar;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public Long getIdAuxiliar() {
		return idAuxiliar;
	}

	public void setIdAuxiliar(Long idAuxiliar) {
		this.idAuxiliar = idAuxiliar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
