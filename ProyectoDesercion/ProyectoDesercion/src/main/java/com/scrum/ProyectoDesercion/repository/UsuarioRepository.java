package com.scrum.ProyectoDesercion.repository;
import com.scrum.ProyectoDesercion.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByCorreoUsuario(String correoUsuario);
}
