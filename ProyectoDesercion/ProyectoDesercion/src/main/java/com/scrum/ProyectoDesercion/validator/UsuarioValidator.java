package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public UsuarioValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // VALIDACIÓN PARA REGISTRO
    public void validarRegistro(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        String correo = usuario.getCorreoUsuario() != null
                ? usuario.getCorreoUsuario().trim()
                : null;

        String contra = usuario.getContraUsuario() != null
                ? usuario.getContraUsuario().trim()
                : null;

        String rol = usuario.getRolUsuario() != null
                ? usuario.getRolUsuario().trim()
                : null;

        // correo obligatorio
        if (correo == null || correo.isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        // dominio Gmail obligatorio
        if (!correo.toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("El correo debe ser de Gmail (@gmail.com)");
        }

        // contraseña obligatoria
        if (contra == null || contra.isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        // rol obligatorio
        if (rol == null || rol.isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }

        // fecha obligatoria
        if (usuario.getCreacionUsuario() == null) {
            throw new IllegalArgumentException("La fecha de creación es obligatoria");
        }

        // Validar si ya existe el usuario
        Optional<Usuario> usuarioExistente =
                usuarioRepository.findByCorreoUsuario(correo);

        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con este correo");
        }
    }

    // VALIDACIÓN PARA LOGIN O BÚSQUEDA
    public Usuario validarUsuarioExistente(String correo) {

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        return usuarioRepository.findByCorreoUsuario(correo.trim())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario no encontrado")
                );
    }
}