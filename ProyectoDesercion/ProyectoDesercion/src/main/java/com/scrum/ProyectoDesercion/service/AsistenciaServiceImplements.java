package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;

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
    public Asistencia getAsistenciaById(Integer id) {
        Asistencia asistencia = repository.findById(id).orElse(null);
        if (asistencia == null) {
            throw new RuntimeException("Asistencia no encontrada");
        }
        return asistencia;
    }

    @Override
    public Asistencia saveAsistencia(Asistencia asistencia) throws RuntimeException {
        return repository.save(asistencia);
    }

    @Override
    public Asistencia updateAsistencia(Integer id, Asistencia asistencia) {
        Asistencia updateAsistencia = repository.findById(id).orElse(null);
        if (updateAsistencia != null) {
            updateAsistencia.setEstado_asistencia(asistencia.getEstado_asistencia());
            updateAsistencia.setFecha_asistencia(asistencia.getFecha_asistencia());
            updateAsistencia.setFk_id_estudiante(asistencia.getFk_id_estudiante());
        } else{
            throw new RuntimeException("Asistencia no encontrada");
        }
        return repository.save(updateAsistencia);
    }

    @Override
    public void deleteAsistencia(Integer id) {
        Asistencia asistencia = repository.findById(id).orElse(null);
        if (asistencia == null) {
            throw new RuntimeException("Asistencia no encontrada");
        }
        repository.delete(asistencia);
    }
}
