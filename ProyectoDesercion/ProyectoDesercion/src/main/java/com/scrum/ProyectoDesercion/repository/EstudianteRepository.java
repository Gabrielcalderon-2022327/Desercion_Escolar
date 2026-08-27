package com.scrum.ProyectoDesercion.repository;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {
    @Query("select e from Estudiante e where e.fk_id_grado = :idGrado")
    List<Estudiante> findByFk_id_grado(@Param("idGrado") Integer idGrado);
}
