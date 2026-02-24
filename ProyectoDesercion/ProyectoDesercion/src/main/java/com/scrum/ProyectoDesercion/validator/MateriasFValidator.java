package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.repository.MateriasfRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MateriasFValidator {

    private final MateriasfRepository materiasfRepository;

    public MateriasFValidator(MateriasfRepository materiasfRepository) {
        this.materiasfRepository = materiasfRepository;
    }

    public void validar(MateriasF materiasF) {

        List<MateriasF> materias = materiasfRepository.findAll();

        String nombre = materiasF.getNombreMateriaF() != null
                ? materiasF.getNombreMateriaF().trim()
                : null;

        Integer idMaestro = materiasF.getIdMaestro();
        Integer idEstudiante = materiasF.getIdEstudiante();

        // Validación nombre obligatorio
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio");
        }

        // Validación descripción obligatoria
        if (materiasF.getDescripcionMateriaF() == null ||
                materiasF.getDescripcionMateriaF().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }

        // Validación fecha obligatoria
        if (materiasF.getFechaAlertaMateriaF() == null) {
            throw new IllegalArgumentException("La fecha de alerta es obligatoria");
        }

        // Fecha no puede ser pasada
        if (materiasF.getFechaAlertaMateriaF().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de alerta no puede ser pasada");
        }

        // Validación idMaestro
        if (idMaestro == null || idMaestro <= 0) {
            throw new IllegalArgumentException("El id del maestro debe ser mayor a 0");
        }

        // Validación idEstudiante
        if (idEstudiante == null || idEstudiante <= 0) {
            throw new IllegalArgumentException("El id del estudiante debe ser mayor a 0");
        }

        // Validación mismo nombre, mismo maestro y mismo estudiante
        for (MateriasF mat : materias) {

            String nombreExistente = mat.getNombreMateriaF() != null
                    ? mat.getNombreMateriaF().trim()
                    : "";

            if (nombre.equals(nombreExistente)
                    && idMaestro.equals(mat.getIdMaestro())
                    && idEstudiante.equals(mat.getIdEstudiante())) {

                throw new IllegalArgumentException(
                        "Ya existe esta materia asignada a este estudiante con este maestro"
                );
            }
        }
    }
}