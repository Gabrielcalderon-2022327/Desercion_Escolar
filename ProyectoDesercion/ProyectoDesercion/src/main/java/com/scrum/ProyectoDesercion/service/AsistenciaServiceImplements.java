package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.AsistenciaRepository;
import com.scrum.ProyectoDesercion.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AsistenciaServiceImplements implements AsistenciaService{
    private final AsistenciaRepository repository;

    public AsistenciaServiceImplements(AsistenciaRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Asistencia> getAllAsistencia() {
        return repository.findAll();
    }
    
    @Override
    public List<Asistencia> getByFechaGrado(LocalDate fecha, Integer id_grado) {
        List<Asistencia> asistencias = repository.findByFechaAndGrado(fecha, id_grado);
        return asistencias;
    }


    @Override
    public void saveAll(List<Asistencia> asistencias) {
        for (Asistencia a : asistencias) {
            Asistencia existente = repository.findByEstudianteAndFecha(a.getFk_id_estudiante(), a.getFecha_asistencia());
            if (existente != null) {
                existente.setEstado_asistencia(a.getEstado_asistencia());
                repository.save(existente);
            } else {
                repository.save(a);
            }
        }
    }
}
