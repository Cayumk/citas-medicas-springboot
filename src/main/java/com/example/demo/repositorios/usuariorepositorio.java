package com.example.demo.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.modelo.Usuario;

@Repository
public interface usuariorepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
}
