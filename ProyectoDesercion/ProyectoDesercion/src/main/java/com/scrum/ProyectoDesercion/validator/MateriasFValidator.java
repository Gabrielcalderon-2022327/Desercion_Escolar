package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.repository.MateriasfRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MateriasFValidator {

    private final MateriasfRepository materiasfRepository;

    public MateriasFValidator(MateriasfRepository materiasfRepository) {
        this.materiasfRepository = materiasfRepository;
    }

    // VALIDACIÓN PARA REGISTRO
    public void validarRegistro(MateriasF materiasF) {

        Integer idMaestro = materiasF.getIdMaestro();
        Integer idEstudiante = materiasF.getIdEstudiante();
        
        // Validación idMaestro
        if (idMaestro == null || idMaestro <= 0) {
            throw new IllegalArgumentException("El id del maestro debe ser mayor a 0");
        }

        // Validación idEstudiante
        if (idEstudiante == null || idEstudiante <= 0) {
            throw new IllegalArgumentException("El id del estudiante debe ser mayor a 0");
        }

    }

    // VALIDACIÓN MATERIA NO ENCONTRADA
    public MateriasF validarMateriaExistente(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la materia es inválido");
        }

        return materiasfRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Materia no encontrada con ID: " + id)
                );
    }
}