package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Economia;
import com.scrum.ProyectoDesercion.service.EconomiaService;
import com.scrum.ProyectoDesercion.validator.EconomiaValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/economia")
public class EconomiaController {
    private final EconomiaService service;
    private final EconomiaValidator validator;

    public EconomiaController(EconomiaService service, EconomiaValidator validator){
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Economia> getAllEstudiantes(){
        return service.getAllEconomias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEconomiaById(@PathVariable Integer id){
        Economia searchedEconomia = service.getEconomiaById(id);
        return new ResponseEntity<>(searchedEconomia, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createdEconomia(@Valid @RequestBody Economia economia){
        validator.validar(economia);
        Economia createdEstudiante = service.saveEconomia(economia);
        return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEconomia(@Valid @RequestBody Economia economia, @PathVariable Integer id){
        validator.validar(economia);
        Economia updatedEconomia = service.updateEconomia(id, economia);
        return new ResponseEntity<>(updatedEconomia, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEconomia(@PathVariable Integer id){
        service.deleteEconomia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
