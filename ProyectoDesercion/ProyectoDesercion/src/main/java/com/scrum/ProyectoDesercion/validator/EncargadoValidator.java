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

        // Validacion para evitar que tenga espacios al final y al inicio, que no tenga cero caracteres
        if(nombreEnc == null || nombreEnc.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del encargado es obligatorio");
        }

        if(apellidoEnc == null || apellidoEnc.trim().isEmpty()){
            throw new IllegalArgumentException("El apellido del encargado es obligatorio");
        }

        // Validacion de la Fecha de nacimiento

        // No sea nula
        if(fechaNacimientoEnc == null ){
            throw new IllegalArgumentException("La fecha de nacimiento del encargado es obligatoria");
        }

        // Para que no sea futura
        if(fechaNacimientoEnc.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento del encargado no puede ser futura");
        }

        if(direccionEnc == null || direccionEnc.trim().isEmpty()){
            throw new IllegalArgumentException("La direccion del encargado es obligatoria");
        }

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
