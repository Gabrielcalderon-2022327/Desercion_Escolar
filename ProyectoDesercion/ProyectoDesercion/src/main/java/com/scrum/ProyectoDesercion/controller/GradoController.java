package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Grado;
import com.scrum.ProyectoDesercion.service.GradoService;
import com.scrum.ProyectoDesercion.validator.GradoValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/grados")
public class GradoController {

    private final GradoService service;
    private final GradoValidator validator;

    public GradoController(GradoService service, GradoValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Grado> getAllGrados(){
        return service.getAllGrado();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGradoById(@PathVariable Integer id){
        Grado searchedGrado = service.getGradoById(id);
        return new ResponseEntity<>(searchedGrado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createGrado(@Valid @RequestBody Grado grado){
        validator.validar(grado);
        Grado createdGrado = service.saveGrado(grado);
        return new ResponseEntity<>(createdGrado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGrado(@Valid @RequestBody Grado grado, @PathVariable Integer id){
        validator.validar(grado);
        Grado updatedGrado = service.updateGrado(id, grado);
        return new ResponseEntity<>(updatedGrado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGrado(@PathVariable Integer id){
        service.deleteGrado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
