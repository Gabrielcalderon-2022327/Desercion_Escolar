package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Maestro;
import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.repository.MaestroRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MaestroValidator {

    private final MaestroRepository maestroRepository;

    public MaestroValidator(MaestroRepository maestroRepository) {
        this.maestroRepository = maestroRepository;
    }

    public void validar(Maestro maestro) {
        List<Maestro> maestros = maestroRepository.findAll();
        String nombre = maestro.getNombreMaestro();
        for (Maestro m : maestros) {
            if (m.getNombreMaestro().trim().equalsIgnoreCase(nombre.trim())) {
                throw new IllegalArgumentException("El maestro ya existe");
            }
        }
    }

    public void validarUpdate(Maestro maestro, Integer id){
        String nombre = maestro.getNombreMaestro().trim();
        Maestro editedMaestro = maestroRepository.findById(id).orElse(null);
        Optional<Maestro> usuarioExistente = maestroRepository.findByNombreMaestro(nombre);
        boolean usuarioExiste;
        if(nombre.equals(editedMaestro.getNombreMaestro())){
            usuarioExiste = false;
        } else if (usuarioExistente.isPresent()){
            usuarioExiste = true;
        } else {
            usuarioExiste = false;
        }
        if (usuarioExiste) {
            throw new IllegalArgumentException("Ya existe un maestro con este nombre");
        }
    }
}