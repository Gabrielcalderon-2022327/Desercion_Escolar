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

        String correo = usuario.getCorreoUsuario() != null
                ? usuario.getCorreoUsuario().trim()
                : null;

        String contra = usuario.getContraUsuario() != null
                ? usuario.getContraUsuario().trim()
                : null;

        String rol = usuario.getRolUsuario() != null
                ? usuario.getRolUsuario().trim()
                : null;

        // dominio Gmail obligatorio
        if (!correo.toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("El correo debe ser de Gmail (@gmail.com)");
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