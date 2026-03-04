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

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        Alerta searchedAlerta = alertaService.getAlertaById(id);
        return new ResponseEntity<>(searchedAlerta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createAlerta(@Valid @RequestBody Alerta alerta) {
            Alerta createdAlerta = alertaService.saveAlerta(alerta);
            return new ResponseEntity<>(createdAlerta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAlerta(@PathVariable Integer id, @Valid @RequestBody Alerta alerta) {
            Alerta updatedAlerta = alertaService.updateAlerta(id, alerta);
            return ResponseEntity.ok(updatedAlerta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAlerta(@PathVariable Integer id) {
            alertaService.deleteAlerta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}