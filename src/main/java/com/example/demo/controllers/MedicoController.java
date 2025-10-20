package com.example.demo.controllers;

import com.example.demo.modelo.Medico;
import com.example.demo.repositorios.medicorepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private medicorepositorio medicoRepo;


    @GetMapping
    public List<Medico> obtenerTodos() {
        return medicoRepo.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Medico> obtenerPorId(@RequestParam Long id) {
        Optional<Medico> medico = medicoRepo.findById(id);
        return medico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @PostMapping("/guardar")
    public String crearMedico(@RequestBody Medico medico) {
         this.medicoRepo.save(medico);
        return "Medico Guardado" ;
    }


    @PutMapping("/actualizar")
    public String actualizarMedico(@RequestParam Long id, @RequestBody Medico medicoActualizado) {
        if (!medicoRepo.existsById(id)) {
            return "El medico no existe o no se encuentra";
        }
        medicoActualizado.setIdMedico(id);
         this.medicoRepo.save(medicoActualizado);
        return "El medico ha sido Actualizado";
    }


    @DeleteMapping("/eliminar")
    public String eliminarMedico(@RequestParam Long id) {
        if (!medicoRepo.existsById(id)) {
            return "El medico no ha sido encontrado";
        }
        medicoRepo.deleteById(id);
        return "El medico a sido eliminado";
    }
    
    @GetMapping("/por-especialidad")
    public List<Medico> obtenerPorEspecialidad(@RequestParam String especialidad) {
        return medicoRepo.findByEspecialidadNombre(especialidad);
    }
}
