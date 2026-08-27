package com.scrum.ProyectoDesercion.repository;

import com.scrum.ProyectoDesercion.entity.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaestroRepository extends JpaRepository<Maestro,Integer> {
    Optional<Maestro> findByNombreMaestro(String nombreMaestro);
}
