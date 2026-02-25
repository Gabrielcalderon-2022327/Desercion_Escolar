package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.service.UsuarioService;
import com.scrum.ProyectoDesercion.validator.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioValidator validator;

    public UsuarioController(UsuarioService usuarioService, UsuarioValidator validator) {
        this.usuarioService = usuarioService;
        this.validator = validator;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Valid @RequestBody Usuario usuario) {

        validator.validarRegistro(usuario);  // 🔥 validación correcta

        Usuario created = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {

        Usuario searchedUsuario = usuarioService.getUsuarioById(id);

        if (searchedUsuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }

        return ResponseEntity.ok(searchedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody Usuario usuario) {

        Usuario usuarioExistente = usuarioService.getUsuarioById(id);

        if (usuarioExistente == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }

        usuario.setIdUsuario(id);

        Usuario updated = usuarioService.updateUsuario(id, usuario);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {

        Usuario usuarioExistente = usuarioService.getUsuarioById(id);

        if (usuarioExistente == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }

        usuarioService.deleteUsuario(id);

        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
}


