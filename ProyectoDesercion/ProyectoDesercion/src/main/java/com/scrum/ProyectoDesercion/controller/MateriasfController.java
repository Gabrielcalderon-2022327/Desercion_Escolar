package com.scrum.ProyectoDesercion.controller;
import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.service.MateriasfService;
import com.scrum.ProyectoDesercion.validator.MateriasFValidator;
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
    private final MateriasFValidator validator;

    public MateriasfController(MateriasfService materiasfService, MateriasFValidator validator) {
        this.materiasfService = materiasfService;
        this.validator = validator;
    }
    @GetMapping
    public List<MateriasF> getAllMateriasF(){return materiasfService.getAllMateriasF();}

    @PostMapping
    public ResponseEntity<Object> createMateriasF(@Valid @RequestBody MateriasF materiasF){
        try {
            validator.validar(materiasF);
            MateriasF created = materiasfService.saveMateriasF(materiasF);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MateriasF> updateMateriasF(@PathVariable Integer id, @Valid @RequestBody MateriasF materiasF) {
        try {
            validator.validar(materiasF);
            MateriasF updated = materiasfService.updateMateriasF(id, materiasF);
            return ResponseEntity.ok(updated);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body((MateriasF) Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriasF(@PathVariable Integer id) {
        materiasfService.deleteMateriasF(id);
        return ResponseEntity.noContent().build();
    }
}
