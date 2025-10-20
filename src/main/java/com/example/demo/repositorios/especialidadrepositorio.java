package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Especialidad;

@Repository
public interface especialidadrepositorio extends JpaRepository<Especialidad,Long> {
}