package com.scrum.ProyectoDesercion.repository;


import com.scrum.ProyectoDesercion.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
}
