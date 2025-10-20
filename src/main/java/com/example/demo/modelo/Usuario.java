package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "Id_Usuario")
    private Long idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "Rh")
    private String rh;

    @ManyToOne
    @JoinColumn(name = "id_Eps")
    private Eps eps;

    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "contrasena")  //
    private String contrasena;

    @Column(name = "rol")
    private String rol;

  
    public Usuario() {
    }

  
    public Usuario(Long idUsuario, String nombre, String apellidos, String telefono, String direccion,
                   String correoElectronico, String rh, Eps eps, Integer edad,
                   String contrasena, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.rh = rh;
        this.eps = eps;
        this.edad = edad;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
