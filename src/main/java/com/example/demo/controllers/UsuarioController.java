package com.example.demo.controllers;

import com.example.demo.modelo.Usuario;
import com.example.demo.repositorios.usuariorepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private usuariorepositorio usuarioRepo;

    // Obtener todos los usuarios
    @GetMapping("/obtener")
    public List<Usuario> obtenerTodos() {
        return usuarioRepo.findAll();
    }

    // Buscar usuario por ID
    @GetMapping("/buscar")
    public String obtenerPorId(@RequestParam Long id) {
        Optional<Usuario> usuario = usuarioRepo.findById(id);
        return usuario.map(u -> "Usuario encontrado: " + u.getNombre())
                      .orElse("Perdón, el usuario con ID " + id + " no se encuentra.");
    }

    // Guardar usuario básico
    @PostMapping("/guardar")
    public String crearUsuario(@RequestBody Usuario usuario) {
        usuarioRepo.save(usuario);
        return "Usuario guardado con nombre: " + usuario.getNombre();
    }

    // Registrar usuario (retorna el objeto)
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioRepo.save(usuario));
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String contrasena = datos.get("contrasena");

        Optional<Usuario> usuario = usuarioRepo.findByCorreoElectronicoAndContrasena(correo, contrasena);

        return usuario.isPresent()
                ? ResponseEntity.ok(usuario.get())
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }


    // Actualizar usuario
    @PutMapping("/actualizar")
    public String actualizarUsuario(@RequestParam Long id, @RequestBody Usuario usuarioActualizado) {
        if (!usuarioRepo.existsById(id)) {
            return "El usuario con ID " + id + " no existe o no se encuentra";
        }
        usuarioActualizado.setIdUsuario(id);
        usuarioRepo.save(usuarioActualizado);
        return "El usuario con ID " + id + " ha sido actualizado";
    }	

    // Eliminar usuario
    @DeleteMapping("/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        if (!usuarioRepo.existsById(id)) {
            return "El usuario con ID " + id + " no ha sido encontrado";
        }
        usuarioRepo.deleteById(id);
        return "El usuario con ID " + id + " ha sido eliminado";
    }
}
