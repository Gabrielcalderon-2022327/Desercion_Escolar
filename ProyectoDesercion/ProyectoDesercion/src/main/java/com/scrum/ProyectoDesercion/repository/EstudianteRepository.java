package com.scrum.ProyectoDesercion.repository;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {
}
