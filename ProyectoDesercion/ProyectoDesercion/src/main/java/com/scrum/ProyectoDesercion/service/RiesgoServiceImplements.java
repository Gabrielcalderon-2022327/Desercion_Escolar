package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.RiesgoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiesgoServiceImplements implements RiesgoService {

    private final RiesgoRepository riesgoRepository;

    public RiesgoServiceImplements(RiesgoRepository riesgoRepository) {
        this.riesgoRepository = riesgoRepository;
    }

    @Override
    public List<Riesgo> listarRiesgos() {
        return riesgoRepository.findAll();
    }

    @Override
    public Riesgo buscarRiesgo(Integer id) {
        Riesgo riesgo = riesgoRepository.findById(id).orElse(null);
        if (riesgo == null) {
            throw new ResourceNotFoundException("Riesgo no encontrada");
        }
        return riesgo;
    }

    @Override
    public Riesgo crearRiesgo(Riesgo riesgo) {
        return riesgoRepository.save(riesgo);
    }

    @Override
    public Riesgo actualizarRiesgo(Integer id, Riesgo riesgo) {
        Riesgo riesgo1 = riesgoRepository.findById(id).orElse(null);
        if (riesgo1 == null) {
            throw new ResourceNotFoundException("Riesgo no encontrada");
        } else {
            riesgo1.setDescripcion_riesgo(riesgo.getDescripcion_riesgo());
            riesgo1.setNivel_riesgo(riesgo.getNivel_riesgo());
            riesgo1.setFk_id_estudiante(riesgo.getFk_id_estudiante());
        }
        return riesgo1;
    }

    @Override
    public void eliminarRiesgo(Integer id) {
        Riesgo riesgo = riesgoRepository.findById(id).orElse(null);
        if (riesgo == null) {
            throw new ResourceNotFoundException("Riesgo no encontrada");
        }
        riesgoRepository.delete(riesgo);
    }
}