package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import com.scrum.ProyectoDesercion.repository.EstudianteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstudianteValidator {
    private final EstudianteRepository estudianteRepository;

    public EstudianteValidator(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void validar(Estudiante estudiante) {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        String nombreCompleto = estudiante.getNombre_estudiante().trim() + " " + estudiante.getApellido_estudiante().trim();
        Integer telefono = estudiante.getTelefono_estudiante();
        Integer idEncargado = estudiante.getFk_id_encargado();
        Integer idGrado = estudiante.getFk_id_grado();

        if (telefono <= 0){
            throw new IllegalArgumentException("El telefono debe ser mayor a 0");
        }
        if (idEncargado <= 0){
            throw new IllegalArgumentException("El id encargado debe ser mayor a 0");
        }
        if (idGrado <= 0){
            throw new IllegalArgumentException("El id grado debe ser mayor a 0");
        }
        String name;
        for (Estudiante est : estudiantes) {
            name = est.getNombre_estudiante().trim() + " " + est.getApellido_estudiante().trim();
            if (nombreCompleto.equals(name)) {
                throw new IllegalArgumentException("El estudiante ya existe");
            }
        }
    }
}
