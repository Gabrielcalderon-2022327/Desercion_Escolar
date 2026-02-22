package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioServiceImplements implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) throws RuntimeException {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer id, Usuario usuario) {
        Usuario usuario1 = usuarioRepository.findById(id).orElse(null);
        if (usuario1 != null) {
            usuario1.setCorreoUsuario(usuario.getCorreoUsuario());
            usuario1.setContraUsuario(usuario.getContraUsuario());
            usuario1.setRolUsuario(usuario.getRolUsuario());
            usuario1.setCreacionUsuario(usuario.getCreacionUsuario());
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioRepository.save(usuario1);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}

