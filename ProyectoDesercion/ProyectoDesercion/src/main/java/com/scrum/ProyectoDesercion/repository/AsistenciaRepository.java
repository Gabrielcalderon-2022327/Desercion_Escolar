package com.scrum.ProyectoDesercion.repository;

import com.scrum.ProyectoDesercion.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Integer> {
    @Query("""
        select a 
        from Asistencia a 
        join a.estudiante e 
        where a.fecha_asistencia = :fecha 
        and e.fk_id_grado = :grado
    """)
    List<Asistencia> findByFechaAndGrado(@Param("fecha") LocalDate fecha, @Param("grado") Integer id_grado);

    @Query(" select a from Asistencia a where a.estudiante.id_estudiante = :id and a.fecha_asistencia = :fecha")
    Asistencia findByEstudianteAndFecha(@Param("id") Integer id,  @Param("fecha") LocalDate fecha);
}
