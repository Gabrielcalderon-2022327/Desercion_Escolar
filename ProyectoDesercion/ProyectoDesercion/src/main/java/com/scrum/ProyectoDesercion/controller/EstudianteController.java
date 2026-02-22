package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import com.scrum.ProyectoDesercion.service.EstudianteService;
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

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estudiante> getAllEstudiantes(){
        return service.getAllEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEstudianteById(@PathVariable Integer id){
        try {
            Estudiante searchedEstudiante = service.getEstudianteById(id);
            return new ResponseEntity<>(searchedEstudiante, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createEstudiante(@Valid @RequestBody Estudiante estudiante){
        try {
            Estudiante createdEstudiante = service.saveEstudiante(estudiante);
            return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEstudiante(@Valid @RequestBody Estudiante estudiante, @PathVariable Integer id){
        try {
            Estudiante updatedEstudiante = service.updateEstudiante(id, estudiante);
            return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstudiante(@PathVariable Integer id){
        try {
            service.deleteEstudiante(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }
}
