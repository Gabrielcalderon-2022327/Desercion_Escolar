package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Grado;
import org.springframework.stereotype.Component;

@Component
public class GradoValidator {
    public void validar(Grado grado){
        Integer idMaestro = grado.getFk_id_maestro();
        String nombreGrad = grado.getNombre_grado();

        // Validacion de idMaestro
        if(idMaestro == null || idMaestro <= 0){
            throw new IllegalArgumentException("El ID del maestro es obligatorio y mayor a 0");
        }

        // Validacion de nombre del grado
        if (nombreGrad == null || nombreGrad.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del grado no puede estar vacio");
        }

        if (nombreGrad.length() > 50) {
            throw new IllegalArgumentException("El nombre del grado es demasiado largo (maximo 50 caracteres)");
        }
    }
}
