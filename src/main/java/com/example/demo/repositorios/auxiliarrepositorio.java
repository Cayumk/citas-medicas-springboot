package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Auxiliar;

@Repository
public interface auxiliarrepositorio extends JpaRepository<Auxiliar,Long> {

}