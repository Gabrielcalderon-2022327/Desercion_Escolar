package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Encargado;
import com.scrum.ProyectoDesercion.repository.EncargadoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EncargadoValidator {

    private final EncargadoRepository encargadoRepository;

    public EncargadoValidator(EncargadoRepository encargadoRepository) {
        this.encargadoRepository = encargadoRepository;
    }

    public void validar(Encargado encargado){

        String nombreEnc = encargado.getNombre_encargado();
        String apellidoEnc = encargado.getApellido_encargado();
        LocalDate fechaNacimientoEnc = encargado.getFecha_nacimiento_encargado();
        String direccionEnc = encargado.getDireccion_encargado();
        Integer telefonoEnc = encargado.getTelefono_encargado();

        if(telefonoEnc == null || (telefonoEnc <= 0)){
            throw new IllegalArgumentException("El numero de telefono no puede ser nulo o menor a cero");
        }

        // La lista de encargados
        List<Encargado> encargadosExistentes = encargadoRepository.findAll();

        // Este bucle recorre la lista
        for(Encargado enc : encargadosExistentes){
            // Comparamos el nombre que entra (encargado) con el de la lista (enc)
            if (enc.getNombre_encargado().trim().equalsIgnoreCase(encargado.getNombre_encargado().trim())) {
                
                throw new IllegalArgumentException("El encargado ya existe");
            }
        }
    }



}
