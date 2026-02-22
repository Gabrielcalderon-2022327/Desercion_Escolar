package com.scrum.ProyectoDesercion.controller;


import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {
    private final AsistenciaService service;

    public AsistenciaController(AsistenciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return service.getAllAsistencia();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAsistenciaById(@PathVariable int id) {
        try {
            Asistencia searchedAsistencia = service.getAsistenciaById(id);
            return new ResponseEntity<>(searchedAsistencia, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAsistencia(@Valid @RequestBody Asistencia asistencia) {
        try {
            Asistencia createdAsistencia = service.saveAsistencia(asistencia);
            return new ResponseEntity<>(createdAsistencia, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAsistencia(@PathVariable int id, @Valid @RequestBody Asistencia asistencia) {
        try {
            Asistencia updatedAsistencia = service.updateAsistencia(id, asistencia);
            return new ResponseEntity<>(updatedAsistencia, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAsistencia(@PathVariable int id) {
        try {
            service.deleteAsistencia(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }
}
