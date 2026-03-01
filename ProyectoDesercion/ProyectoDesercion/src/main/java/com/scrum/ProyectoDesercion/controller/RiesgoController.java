package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.service.RiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riesgo")
public class RiesgoController {

    @Autowired
    private RiesgoService riesgoService;

    @GetMapping
    public ResponseEntity<List<Riesgo>> listarRiesgos() {
        return new ResponseEntity<>(riesgoService.listarRiesgos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Riesgo> buscarRiesgo(@PathVariable Integer id) {
        Riesgo riesgo = riesgoService.buscarRiesgo(id);
        if (riesgo != null) {
            return new ResponseEntity<>(riesgo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Riesgo> crearRiesgo(@RequestBody Riesgo riesgo) {
        Riesgo nuevoRiesgo = riesgoService.crearRiesgo(riesgo);
        return new ResponseEntity<>(nuevoRiesgo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Riesgo> actualizarRiesgo(@PathVariable Integer id, @RequestBody Riesgo riesgo) {
        try {
            Riesgo riesgoActualizado = riesgoService.actualizarRiesgo(id, riesgo);
            return new ResponseEntity<>(riesgoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRiesgo(@PathVariable Integer id) {
        riesgoService.eliminarRiesgo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 sin contenido
    }
}