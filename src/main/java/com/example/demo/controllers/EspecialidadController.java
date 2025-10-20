package com.example.demo.controllers;

import com.example.demo.modelo.Especialidad;
import com.example.demo.repositorios.especialidadrepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private especialidadrepositorio especialidadRepo;

    @GetMapping
    public List<String> obtenerTodos() {
        return especialidadRepo.findAll()
        		.stream()
				.map(Especialidad::getNombre)
				.collect(Collectors.toList());
    }


    @GetMapping("/buscar")
    public ResponseEntity<Especialidad> obtenerPorId(@RequestParam Long id) {
        Optional<Especialidad> especialidad = especialidadRepo.findById(id);
        return especialidad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/guardar")
    public String crearEspecialidad(@RequestBody Especialidad especialidad) {
        especialidadRepo.save(especialidad);
        return "Especialidad guardada exitosamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarEspecialidad(@RequestParam Long id, @RequestBody Especialidad especialidadActualizada) {
        if (!especialidadRepo.existsById(id)) {
            return "La especialidad no existe o no se encuentra.";
        }
        especialidadActualizada.setIdEspecialidad(id);
        especialidadRepo.save(especialidadActualizada);
        return "Especialidad actualizada exitosamente.";
    }


    @DeleteMapping("/eliminar")
    public String eliminarEspecialidad(@RequestParam Long id) {
        if (!especialidadRepo.existsById(id)) {
            return "La especialidad no ha sido encontrada.";
        }
        especialidadRepo.deleteById(id);
        return "Especialidad eliminada exitosamente.";
    }
}
