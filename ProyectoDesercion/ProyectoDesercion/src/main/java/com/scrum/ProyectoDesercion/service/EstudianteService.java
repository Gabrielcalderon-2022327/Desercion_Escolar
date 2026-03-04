package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstudianteService {
    List<Estudiante> getAllEstudiantes();
    Estudiante getEstudianteById(Integer id);
    Estudiante saveEstudiante(Estudiante estudiante);
    Estudiante updateEstudiante(Integer id, Estudiante estudiante);
    void deleteEstudiante(Integer id);
}
