package com.scrum.ProyectoDesercion.controller;


import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.service.AsistenciaService;
import com.scrum.ProyectoDesercion.validator.AsistenciaValidator;
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
    private final AsistenciaValidator validator;

    public AsistenciaController(AsistenciaService service, AsistenciaValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return service.getAllAsistencia();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAsistenciaById(@PathVariable int id) {
        Asistencia searchedAsistencia = service.getAsistenciaById(id);
        return new ResponseEntity<>(searchedAsistencia, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createAsistencia(@Valid @RequestBody Asistencia asistencia) {
        validator.validar(asistencia);
        Asistencia createdAsistencia = service.saveAsistencia(asistencia);
        return new ResponseEntity<>(createdAsistencia, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAsistencia(@PathVariable int id, @Valid @RequestBody Asistencia asistencia) {
        validator.validar(asistencia);
        Asistencia updatedAsistencia = service.updateAsistencia(id, asistencia);
        return new ResponseEntity<>(updatedAsistencia, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAsistencia(@PathVariable int id) {
        service.deleteAsistencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
