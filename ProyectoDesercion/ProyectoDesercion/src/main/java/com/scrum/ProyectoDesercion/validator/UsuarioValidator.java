package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public UsuarioValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validar(Usuario usuario) {

        List<Usuario> usuarios = usuarioRepository.findAll();

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
        if (usuario.getCorreoUsuario() == null || usuario.getCorreoUsuario().isEmpty()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        // El correo tiene que tener @gmail.com
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

        // fecha de creación obligatoria
        if (usuario.getCreacionUsuario() == null) {
            throw new IllegalArgumentException("La fecha de creación es obligatoria");
        }

        // Validación para correos dobles
        for (Usuario u : usuarios) {
            String correoExistente = u.getCorreoUsuario() != null
                    ? u.getCorreoUsuario().trim()
                    : "";

            if (correo.equalsIgnoreCase(correoExistente)) {
                throw new IllegalArgumentException("Ya existe un usuario con este correo");
            }
        }
    }
}
