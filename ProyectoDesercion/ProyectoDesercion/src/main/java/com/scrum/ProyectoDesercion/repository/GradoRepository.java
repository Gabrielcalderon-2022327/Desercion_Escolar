package com.scrum.ProyectoDesercion.repository;

import com.scrum.ProyectoDesercion.entity.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepository extends JpaRepository<Grado,Integer> {
}
