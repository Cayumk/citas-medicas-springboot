package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Identificacion")
    private String identificacion;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Correo_electronico")
    private String correoElectronico;

    @ManyToOne
    @JoinColumn(name = "id_eps")
    private Eps eps;

    @ManyToOne
    @JoinColumn(name = "id_Especialidad")
    private Especialidad especialidad;

    @Column(name = "Registro_medico")
    private String registroMedico;

    public Medico() {
    }

	public Medico(Long idMedico, String nombre, String apellidos, String identificacion, String telefono,
			String direccion, String correoElectronico, Eps eps, Especialidad especialidad, String registroMedico) {
		super();
		this.idMedico = idMedico;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.identificacion = identificacion;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.eps = eps;
		this.especialidad = especialidad;
		this.registroMedico = registroMedico;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getRegistroMedico() {
		return registroMedico;
	}

	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
	}

}
