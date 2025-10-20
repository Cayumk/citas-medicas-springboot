package com.example.demo.controllers;

import com.example.demo.modelo.Cita;
import com.example.demo.modelo.Medico;
import com.example.demo.modelo.SolicitudCitaDTO;
import com.example.demo.repositorios.citarepositorio;
import com.example.demo.repositorios.medicorepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private citarepositorio citaRepositorio;

    @Autowired
    private medicorepositorio medicoRepositorio;
    
    
    @GetMapping("/obtener")
    public List<Cita> obtenerTodas() {
        return citaRepositorio.findAll();
    }

 
    @GetMapping("/buscar")
    public String obtenerPorId(@RequestParam Long id) {
        Optional<Cita> cita = citaRepositorio.findById(id);
        if (cita.isPresent()) {
            Cita c = cita.get();
            return "Cita encontrada: " + c.getUsuario() + " con doctor " + c.getMedico();
        } else {
            return "Perdón, la cita con ID " + id + " no se encuentra.";
        }
    }


    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody SolicitudCitaDTO dto) {
   
        return ResponseEntity.ok("Cita creada");
    }

    @PutMapping("/actualizar")
    public String actualizarCita(@RequestParam Long id, @RequestBody Cita citaActualizada) {
        if (!citaRepositorio.existsById(id)) {
            return "La cita con ID " + id + " no existe o no se encuentra";
        }
        citaActualizada.setIdCita(id);
        citaRepositorio.save(citaActualizada);
        return "La cita con ID " + id + " ha sido actualizada";
    }

    @DeleteMapping("/eliminar")
    public String eliminarCita(@RequestParam Long id) {
        if (!citaRepositorio.existsById(id)) {
            return "La cita con ID " + id + " no ha sido encontrada";
        }
        citaRepositorio.deleteById(id);
        return "La cita con ID " + id + " ha sido eliminada";
    }
    
    @PostMapping
    public ResponseEntity<?> agendarCita(@RequestBody SolicitudCitaDTO solicitud) {
        Optional<Medico> medicoOpt = medicoRepositorio.findById(solicitud.getIdMedico());

        if (medicoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Médico no encontrado");
        }

    
        Medico medico = medicoOpt.get();
        
        if (!medico.getEps().getNombre().equalsIgnoreCase(solicitud.getEpsUsuario())) {
            return ResponseEntity.badRequest().body("El médico no pertenece a la EPS seleccionada");
        }
        
        boolean yaReservada = citaRepositorio.existsByMedico_IdMedicoAndFecha(medico.getIdMedico(), solicitud.getFecha());
        if (yaReservada) {
        	return ResponseEntity.badRequest().body("La fecha seleccionada ya está ocupada por otro paciente");
        }
        
        Cita cita = new Cita();
        cita.setMedico(medico);
        cita.setFecha(solicitud.getFecha());
        cita.setEstado("pendiente");
        cita.setNombreUsuario(solicitud.getNombreUsuario());
        cita.setIdentificacionUsuario(solicitud.getIdentificacionUsuario());
        cita.setEpsUsuario(solicitud.getEpsUsuario());
        cita.setNumeroDeAutorizacion(generarNumeroDeAutorizacion());

        citaRepositorio.save(cita);
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Cita agendada exitosamente");
        respuesta.put("numeroDeAutorizacion", cita.getNumeroDeAutorizacion());
        
        return ResponseEntity.ok(respuesta);
    }
    
    private String generarNumeroDeAutorizacion() {
    	return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    @GetMapping("/verificar-disponibilidad")
    public ResponseEntity<?> verificarDisponibilidad(@RequestParam Long idMedico,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        boolean existe = citaRepositorio.existsByMedico_IdMedicoAndFecha(idMedico, fecha);

        if (existe) {
            return ResponseEntity.ok().body(Map.of("disponible", false, "mensaje", "Ya existe una cita para ese médico en esa fecha."));
        } else {
            return ResponseEntity.ok().body(Map.of("disponible", true, "mensaje", "Fecha disponible."));
        }
    }
    
}
