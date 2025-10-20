package com.example.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_Medico")
    private Medico medico;

    @Column(name = "Fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "Numero_de_autorizacion")
    private String numeroDeAutorizacion;

    @ManyToOne
    @JoinColumn(name = "id_Auxiliar")
    private Auxiliar auxiliar;
    
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    
    @Column(name = "identificacion_usuario")
    private String identificacionUsuario;
    
    @Column(name = "eps_usuario")
    private String epsUsuario;

    public Cita() {
    }

	public Cita(Long idCita, Usuario usuario, Medico medico, LocalDate fecha, String estado,
			String numeroDeAutorizacion, Auxiliar auxiliar) {
		super();
		this.idCita = idCita;
		this.usuario = usuario;
		this.medico = medico;
		this.fecha = fecha;
		this.estado = estado;
		this.numeroDeAutorizacion = numeroDeAutorizacion;
		this.auxiliar = auxiliar;
	}

	public Long getIdCita() {
		return idCita;
	}

	public void setIdCita(Long idCita) {
		this.idCita = idCita;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroDeAutorizacion() {
		return numeroDeAutorizacion;
	}

	public void setNumeroDeAutorizacion(String numeroDeAutorizacion) {
		this.numeroDeAutorizacion = numeroDeAutorizacion;
	}

	public Auxiliar getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Auxiliar auxiliar) {
		this.auxiliar = auxiliar;
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
