package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.service.RiesgoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riesgo")
public class RiesgoController {

    private final RiesgoService riesgoService;

    public RiesgoController(RiesgoService riesgoService) {
        this.riesgoService = riesgoService;
    }

    @GetMapping
    public ResponseEntity<List<Riesgo>> listarRiesgos() {
        return new ResponseEntity<>(riesgoService.listarRiesgos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        Riesgo searchedRiesgo = riesgoService.buscarRiesgo(id);
        return new ResponseEntity<>(searchedRiesgo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createRiesgo(@Valid @RequestBody Riesgo riesgo) {
        Riesgo createdRiesgo = riesgoService.crearRiesgo(riesgo);
        return new ResponseEntity<>(createdRiesgo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRiesgo(@PathVariable Integer id, @Valid @RequestBody Riesgo riesgo) {
        Riesgo updatedRiesgo = riesgoService.actualizarRiesgo(id, riesgo);
        return ResponseEntity.ok(updatedRiesgo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRiesgo(@PathVariable Integer id) {
        riesgoService.eliminarRiesgo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}