package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface UsuarioService {
    List<Usuario> getAllEmpleados();
    Usuario getEmpleadoById(Integer id);
    Usuario saveEmpleado (Usuario usuario) throws RuntimeException;
    Usuario updateEmpleado(Integer id, Usuario usuario);
    void deleteEmpleado(Integer id);
}
