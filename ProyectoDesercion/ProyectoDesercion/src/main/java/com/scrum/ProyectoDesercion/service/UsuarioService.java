package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario) throws RuntimeException;
    Usuario updateUsuario(Integer id, Usuario usuario);
    void deleteUsuario(Integer id);
}
