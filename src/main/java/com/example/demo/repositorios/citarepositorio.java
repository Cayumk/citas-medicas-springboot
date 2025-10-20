package com.example.demo.repositorios;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Cita;

@Repository
public interface citarepositorio extends JpaRepository<Cita,Long> {
	boolean existsByMedico_IdMedicoAndFecha(Long idMedico, LocalDate fecha);
}
