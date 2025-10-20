package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "eps")
public class Eps {

    @Id
    @Column(name = "id_eps")
    private Long idEps;

    @Column(name = "Nombre")
    private String nombre;

    public Eps() {
    }

	public Eps(Long idEps, String nombre) {
		super();
		this.idEps = idEps;
		this.nombre = nombre;
	}

	public Long getIdEps() {
		return idEps;
	}

	public void setIdEps(Long idEps) {
		this.idEps = idEps;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
