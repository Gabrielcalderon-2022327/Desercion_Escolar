package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.service.EstudianteService;
import com.scrum.ProyectoDesercion.validator.EstudianteValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final EstudianteService service;
    private final EstudianteValidator validator;

    public EstudianteController(EstudianteService service, EstudianteValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Estudiante> getAllEstudiantes(){
        return service.getAllEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEstudianteById(@PathVariable Integer id){
        Estudiante searchedEstudiante = service.getEstudianteById(id);
        return new ResponseEntity<>(searchedEstudiante, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createEstudiante(@Valid @RequestBody Estudiante estudiante){
        validator.validar(estudiante);
        Estudiante createdEstudiante = service.saveEstudiante(estudiante);
        return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEstudiante(@Valid @RequestBody Estudiante estudiante, @PathVariable Integer id){
        validator.validar(estudiante);
        Estudiante updatedEstudiante = service.updateEstudiante(id, estudiante);
        return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstudiante(@PathVariable Integer id){
        service.deleteEstudiante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
