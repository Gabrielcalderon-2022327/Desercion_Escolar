package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Asistencia;
import org.springframework.stereotype.Component;

@Component
public class AsistenciaValidator {

    public void validar(Asistencia asistencia){
        Integer idEstudiante = asistencia.getFk_id_estudiante();
        String estadoAsistencia = asistencia.getEstado_asistencia().trim();

        if (idEstudiante <= 0){
            throw new IllegalArgumentException("El id del estudiante debe ser mayor a 0");
        }
        if (!estadoAsistencia.equals("presente") && !estadoAsistencia.equals("ausente") && !estadoAsistencia.equals("tardanza")){
            throw new IllegalArgumentException("El estado de asistencia debe ser alguna de las siguientes opciones: presente, ausente, tardanza");
        }
    }
}
