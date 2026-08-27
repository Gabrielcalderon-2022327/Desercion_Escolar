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
        String correo = usuario.getCorreoUsuario().trim();

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

    public void validarUpdate(Usuario usuario, Integer id){
        String correo = usuario.getCorreoUsuario().trim();
        Usuario editedUser = usuarioRepository.findById(id).orElse(null);

        // dominio Gmail obligatorio
        if (!correo.toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("El correo debe ser de Gmail (@gmail.com)");
        }

        // Validar si ya existe el usuario
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreoUsuario(correo);
        boolean usuarioExiste;
        if(correo.equals(editedUser.getCorreoUsuario())){
            usuarioExiste = false;
        } else if (usuarioExistente.isPresent()){
            usuarioExiste = true;
        } else {
            usuarioExiste = false;
        }

        if (usuarioExiste) {
            throw new IllegalArgumentException("Ya existe un usuario con este correo");
        }
    }

    // VALIDACIÓN PARA LOGIN O BÚSQUEDA
    public Usuario validarUsuarioExistente(String correo) {

        return usuarioRepository.findByCorreoUsuario(correo.trim())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario no encontrado")
                );
    }
}