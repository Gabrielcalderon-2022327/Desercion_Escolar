package com.scrum.ProyectoDesercion.repository;


import com.scrum.ProyectoDesercion.entity.Encargado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadoRepository extends JpaRepository<Encargado, Integer> {
}
