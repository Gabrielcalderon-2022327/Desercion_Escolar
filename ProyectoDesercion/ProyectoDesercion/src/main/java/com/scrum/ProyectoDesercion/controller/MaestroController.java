package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Maestro;
import com.scrum.ProyectoDesercion.service.MaestroService;
import com.scrum.ProyectoDesercion.validator.MaestroValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maestros")
public class MaestroController {
    private final MaestroService service;
    private final MaestroValidator validator;

    public MaestroController(MaestroService service, MaestroValidator validator){
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Maestro> getAllMaestros(){
        return service.getAllMaestros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMaestroById(@PathVariable Integer id){
        Maestro searchedMaestro = service.getMaestroById(id);
        return new ResponseEntity<>(searchedMaestro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createMaestro(@Valid @RequestBody Maestro maestro){
        validator.validar(maestro);
        Maestro createdMaestro = service.saveMaestro(maestro);
        return new ResponseEntity<>(createdMaestro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMaestro(@Valid @RequestBody Maestro maestro, @PathVariable Integer id){
        validator.validar(maestro);
        Maestro updatedMaestro = service.updateMaestro(id, maestro);
        return new ResponseEntity<>(updatedMaestro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMaestro(@PathVariable Integer id){
        service.deleteMaestro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
