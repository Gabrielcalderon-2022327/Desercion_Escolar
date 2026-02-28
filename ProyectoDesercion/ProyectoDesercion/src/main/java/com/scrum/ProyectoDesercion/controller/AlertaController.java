package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Alerta;
import com.scrum.ProyectoDesercion.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerta")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }


    @GetMapping
    public List<Alerta> getAllAlerta() {
        return alertaService.getAllAlerta();
    }

    @PostMapping
    public ResponseEntity<Object> createAlerta(@Valid @RequestBody Alerta alerta) {
        try {
            Alerta createdAlerta = alertaService.saveAlerta(alerta);
            return new ResponseEntity<>(createdAlerta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAlerta(@PathVariable Integer id, @Valid @RequestBody Alerta alerta) {
        try {
            Alerta updatedAlerta = alertaService.updateAlerta(id, alerta);
            return ResponseEntity.ok(updatedAlerta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAlerta(@PathVariable Integer id) {
        try {
            alertaService.deleteAlerta(id);
            return ResponseEntity.ok("La alerta fue eliminada con éxito: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo eliminar: " + e.getMessage());
        }
    }
}