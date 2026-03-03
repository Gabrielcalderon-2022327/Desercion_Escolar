package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Alerta;
import org.springframework.stereotype.Component;

@Component
public class AlertaValidator {

    public void validar(Alerta alerta){
    int riesgo = alerta.getFk_id_riesgo();
    if (riesgo<=0 )
        throw new IllegalArgumentException("el id de riesgo debe de ser mayor a 0");
    }

}
