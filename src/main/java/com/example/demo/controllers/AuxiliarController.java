package com.example.demo.controllers;

import com.example.demo.modelo.Auxiliar;
import com.example.demo.repositorios.auxiliarrepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auxiliares")
public class AuxiliarController {

    @Autowired
    private auxiliarrepositorio auxiliarRepo;

    @GetMapping("/obtener")
    public List<Auxiliar> obtenerTodos() {
        return auxiliarRepo.findAll();
    }

    @GetMapping("/buscar")
    public String obtenerPorId(@RequestParam Long id) {
        Optional<Auxiliar> auxiliar = auxiliarRepo.findById(id);
        return auxiliar.map(a -> "Auxiliar encontrado: " + a.getNombre())
                .orElse("Perdón, el auxiliar con ID " + id + " no se encuentra.");
    }

    @PostMapping("/guardar")
    public String crearAuxiliar(@RequestBody Auxiliar auxiliar) {
        auxiliarRepo.save(auxiliar);
        return "Auxiliar guardado con nombre: " + auxiliar.getNombre();
    }

    @PutMapping("/actualizar")
    public String actualizarAuxiliar(@RequestParam Long id, @RequestBody Auxiliar auxiliarActualizado) {
        if (!auxiliarRepo.existsById(id)) {
            return "El auxiliar con ID " + id + " no existe o no se encuentra.";
        }
        auxiliarActualizado.setIdAuxiliar(id);
        auxiliarRepo.save(auxiliarActualizado);
        return "El auxiliar con ID " + id + " ha sido actualizado.";
    }

    @DeleteMapping("/eliminar")
    public String eliminarAuxiliar(@RequestParam Long id) {
        if (!auxiliarRepo.existsById(id)) {
            return "El auxiliar con ID " + id + " no ha sido encontrado.";
        }
        auxiliarRepo.deleteById(id);
        return "El auxiliar con ID " + id + " ha sido eliminado.";
    }

    // 🔐 NUEVO: Login de auxiliares
    @PostMapping("/login")
    public ResponseEntity<?> loginAuxiliar(@RequestBody Map<String, String> datos) {
        String nombre = datos.get("nombre");
        String contrasena = datos.get("contrasena");

        List<Auxiliar> auxiliares = auxiliarRepo.findAll();

        for (Auxiliar a : auxiliares) {
            if (a.getNombre().equals(nombre) && a.getContrasena().equals(contrasena)) {
                Map<String, Object> respuesta = new HashMap<>();
                respuesta.put("id", a.getIdAuxiliar());
                respuesta.put("nombre", a.getNombre());
                respuesta.put("rol", "auxiliar");
                return ResponseEntity.ok(respuesta);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}
