package com.example.demo.controllers;

import com.example.demo.modelo.Eps;
import com.example.demo.repositorios.epsrepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eps")
public class EpsController {

    @Autowired
    private epsrepositorio epsRepo;

    @GetMapping("/obtener")
    public List<Eps> obtenerTodos() {
        return epsRepo.findAll();
    }


    @GetMapping("/buscar")
    public String obtenerPorId(@RequestParam Long id) {
        Optional<Eps> eps = epsRepo.findById(id);
        return eps.map(e -> "EPS encontrada: " + e.getNombre())
                .orElse("Perdón, la EPS con ID " + id + " no se encuentra.");
    }

    @PostMapping("/guardar")
    public String crearEps(@RequestBody Eps eps) {
        epsRepo.save(eps);
        return "EPS guardada con nombre: " + eps.getNombre();
    }

    @PutMapping("/actualizar")
    public String actualizarEps(@RequestParam Long id, @RequestBody Eps epsActualizada) {
        if (!epsRepo.existsById(id)) {
            return "La EPS con ID " + id + " no existe o no se encuentra";
        }
        epsActualizada.setIdEps(id);
        epsRepo.save(epsActualizada);
        return "La EPS con ID " + id + " ha sido actualizada";
    }

    @DeleteMapping("/eliminar")
    public String eliminarEps(@RequestParam Long id) {
        if (!epsRepo.existsById(id)) {
            return "La EPS con ID " + id + " no ha sido encontrada";
        }
        epsRepo.deleteById(id);
        return "La EPS con ID " + id + " ha sido eliminada";
    }
    
    
}
