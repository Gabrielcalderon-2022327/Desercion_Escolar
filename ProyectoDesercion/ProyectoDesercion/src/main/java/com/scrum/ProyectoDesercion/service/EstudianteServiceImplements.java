package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImplements implements EstudianteService{
    private final EstudianteRepository repository;

    public EstudianteServiceImplements(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return repository.findAll();
    }

    @Override
    public Optional <Estudiante> getEstudianteById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Estudiante saveEstudiante(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Integer id, Estudiante estudiante) {
        Estudiante updateEstudiante = repository.findById(id).orElse(null);
        if (updateEstudiante != null) {
            updateEstudiante.setNombre_estudiante(estudiante.getNombre_estudiante());
            updateEstudiante.setApellido_estudiante(estudiante.getApellido_estudiante());
            updateEstudiante.setFecha_nacimiento_estudiante(estudiante.getFecha_nacimiento_estudiante());
            updateEstudiante.setDireccion_estudiante(estudiante.getDireccion_estudiante());
            updateEstudiante.setTelefono_estudiante(estudiante.getTelefono_estudiante());
            updateEstudiante.setFk_id_encargado(estudiante.getFk_id_encargado());
            updateEstudiante.setFk_id_grado(estudiante.getFk_id_grado());
        } else {
            throw new ResourceNotFoundException("Estudiante no encontrado");
        }
        return repository.save(updateEstudiante);
    }

    @Override
    public void deleteEstudiante(Integer id) {
        Estudiante estudiante = repository.findById(id).orElse(null);
        if (estudiante == null) {
            throw new ResourceNotFoundException("Estudiante no encontrado");
        }
        repository.delete(estudiante);
    }
}

