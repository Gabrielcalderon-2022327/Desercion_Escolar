package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.service.MateriasfService;
import com.scrum.ProyectoDesercion.validator.MateriasFValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiasf")
public class MateriasfController {

    private final MateriasfService materiasfService;
    private final MateriasFValidator validator;

    public MateriasfController(MateriasfService materiasfService,
                               MateriasFValidator validator) {
        this.materiasfService = materiasfService;
        this.validator = validator;
    }

    @GetMapping
    public List<MateriasF> getAllMateriasF() {
        return materiasfService.getAllMateriasF();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriasF> getMateriasFById(@PathVariable Integer id) {
        MateriasF materia = validator.validarMateriaExistente(id);

        return ResponseEntity.ok(materia);
    }

    @PostMapping
    public ResponseEntity<MateriasF> createMateriasF(@Valid @RequestBody MateriasF materiasF) {

        validator.validarRegistro(materiasF);

        MateriasF created = materiasfService.saveMateriasF(materiasF);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriasF> updateMateriasF(@PathVariable Integer id, @Valid @RequestBody MateriasF materiasF) {
        validator.validarMateriaExistente(id);

        MateriasF updated = materiasfService.updateMateriasF(id, materiasF);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMateriasF(@PathVariable Integer id) {
        validator.validarMateriaExistente(id);

        materiasfService.deleteMateriasF(id);

        return ResponseEntity.ok("Materia eliminada con éxito");
    }
}
