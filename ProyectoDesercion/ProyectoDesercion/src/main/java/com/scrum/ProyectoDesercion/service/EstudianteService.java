package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EstudianteService {
    List<Estudiante> getAllEstudiantes();
    Optional<Estudiante> getEstudianteById(Integer id);
    Estudiante saveEstudiante(Estudiante estudiante);
    Estudiante updateEstudiante(Integer id, Estudiante estudiante);
    void deleteEstudiante(Integer id);
    List<Estudiante> getEstudiantesByGrupo(Integer id_grado);
}
