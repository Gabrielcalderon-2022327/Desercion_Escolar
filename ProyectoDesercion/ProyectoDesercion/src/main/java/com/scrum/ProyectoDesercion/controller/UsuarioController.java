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
    public List<Usuario> getAllUsuarios(){return usuarioService.getAllUsuarios();}

    @PostMapping
    public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Usuario usuario){
            validator.validar(usuario);
            Usuario created = usuarioService.saveUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable int id) {
        Usuario searchedUsuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(searchedUsuario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
            usuario.setIdUsuario(id);   // Para que el validator ignore el mismo usuario
            validator.validar(usuario);
            Usuario updated = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
}


