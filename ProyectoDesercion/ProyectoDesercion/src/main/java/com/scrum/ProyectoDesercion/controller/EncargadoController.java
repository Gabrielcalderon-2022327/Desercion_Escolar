package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Encargado;
import com.scrum.ProyectoDesercion.service.EncargadoService;
import com.scrum.ProyectoDesercion.validator.EncargadoValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encargados")
public class EncargadoController {

    private final EncargadoService service;
    private final EncargadoValidator validator;

    public EncargadoController(EncargadoService service, EncargadoValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Encargado> getAllEncargados(){
        return service.getAllEncargado();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEncargadoById(@PathVariable Integer id){
        Encargado searchedEncargado = service.getEncargadoById(id);
        return new ResponseEntity<>(searchedEncargado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createEncargado(@Valid @RequestBody Encargado encargado){
        validator.validar(encargado);
        Encargado createdEncargado = service.saveEncargado(encargado);
        return new ResponseEntity<>(createdEncargado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEncargado(@Valid @RequestBody Encargado encargado, @PathVariable Integer id){
        validator.validar(encargado);
        Encargado updatedEncargado = service.updateEncargado(id, encargado);
        return new ResponseEntity<>(updatedEncargado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEncargado(@PathVariable Integer id){
        service.deleteEncargado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
