package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    @GetMapping
    public List<Usuario> getAllUsuarios(){return usuarioService.getAllUsuarios();}

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Usuario usuario){
        try {
            Usuario createdUsuario = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("Error", e.getMessage()), HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {

        Usuario updatedEmpleado = usuarioService.updateUsuario(id, usuario);

        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}


