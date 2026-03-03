package com.scrum.ProyectoDesercion.validator;


import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.repository.RiesgoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RiesgoValidator {
    private final RiesgoRepository riesgoRepository;

    public RiesgoValidator(RiesgoRepository riesgoRepository) {
        this.riesgoRepository = riesgoRepository;
    }

    public void validar (Riesgo riesgo){
        List<Riesgo> listaRiesgos = riesgoRepository.findAll();
        String nivelRiesgo = riesgo.getNivel_riesgo();
        int idEstudiante = riesgo.getFk_id_estudiante();
        if (idEstudiante<=0 ){
            throw new IllegalArgumentException("el id de estudiante debe de ser mayor a 0");
        }

        if (!nivelRiesgo.equals("Bajo")&& !nivelRiesgo.equals("Medio")&& !nivelRiesgo.equals("Alto")&& !nivelRiesgo.equals("Critico")){
            throw new IllegalArgumentException("el nivel de riesgo solo puede ser Bajo, Medio, Alto o Critico");
        }

        for (Riesgo item: listaRiesgos){
            if (riesgo.getFk_id_estudiante().equals(item.getFk_id_estudiante())){
                throw new IllegalArgumentException("el id del estudiante tiene que ser unico");
            }
        }

    }

}
