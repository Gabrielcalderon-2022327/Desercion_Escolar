package com.scrum.ProyectoDesercion.controller;
import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.service.MateriasfService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/materiasf")
public class MateriasfController {
    private final MateriasfService materiasfService;

    public MateriasfController(MateriasfService materiasfService) { this.materiasfService = materiasfService; }
    @GetMapping
    public List<MateriasF> getAllMateriasF(){return materiasfService.getAllMateriasF();}

    @PostMapping
    public ResponseEntity<Object> createMateriasF(@Valid @RequestBody MateriasF materiasF){
        try {
            MateriasF createdUsuario = materiasfService.saveMateriasF(materiasF);
            return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("Error", e.getMessage()), HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MateriasF> updateMateriasF(@PathVariable Integer id, @Valid @RequestBody MateriasF materiasF) {

        MateriasF updatedEmpleado = materiasfService.updateMateriasF(id, materiasF);

        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriasF(@PathVariable Integer id) {
        materiasfService.deleteMateriasF(id);
        return ResponseEntity.noContent().build();
    }
}
