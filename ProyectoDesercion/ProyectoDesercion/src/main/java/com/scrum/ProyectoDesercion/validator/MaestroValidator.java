package com.scrum.ProyectoDesercion.validator;

import com.scrum.ProyectoDesercion.entity.Maestro;
import com.scrum.ProyectoDesercion.repository.MaestroRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaestroValidator {

    private final MaestroRepository maestroRepository;

    public MaestroValidator(MaestroRepository maestroRepository) {
        this.maestroRepository = maestroRepository;
    }

    public void validar(Maestro maestro) {

        String nombre = maestro.getNombreMaestro();
        String especialidad = maestro.getEspecialidadMaestro();
        Integer telefono = maestro.getTelefonoMaestro();
        Integer idUsuario = maestro.getIdUsuario();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del maestro es obligatorio");
        }
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (telefono == null || telefono <= 0) {
            throw new IllegalArgumentException("El teléfono debe ser mayor a 0");
        }
        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("El id del usuario debe ser mayor a 0");
        }

        List<Maestro> maestros = maestroRepository.findAll();

        for (Maestro m : maestros) {
            if (m.getNombreMaestro().trim().equalsIgnoreCase(nombre.trim())) {
                throw new IllegalArgumentException("El maestro ya existe");
            }
        }
    }
}