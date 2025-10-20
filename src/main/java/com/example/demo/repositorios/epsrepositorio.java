package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Eps;

@Repository
public interface epsrepositorio extends JpaRepository<Eps,Long> {

}
