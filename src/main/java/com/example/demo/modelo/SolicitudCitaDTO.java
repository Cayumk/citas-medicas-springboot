package com.example.demo.modelo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class SolicitudCitaDTO {

	private Long idMedico;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private String nombreUsuario;
    private String identificacionUsuario;
    
    private String epsUsuario;
	
	public SolicitudCitaDTO() {		
	}
	
	public SolicitudCitaDTO(Long idMedico, LocalDate fecha, String nombreUsuario,
			String identificacionUsuario, String epsUsuario) {
		super();
		this.idMedico = idMedico;
		this.fecha = fecha;
		this.nombreUsuario = nombreUsuario;
		this.identificacionUsuario = identificacionUsuario;
		this.epsUsuario = epsUsuario;
	}
	
	public Long getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}
	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}
	
	public String getEpsUsuario() {
		return epsUsuario;
	}
	public void setEpsUsuario(String epsUsuario) {
		this.epsUsuario = epsUsuario;
	}

	

}
