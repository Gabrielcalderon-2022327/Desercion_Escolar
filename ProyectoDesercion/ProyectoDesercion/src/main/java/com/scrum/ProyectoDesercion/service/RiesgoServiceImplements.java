package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.repository.RiesgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiesgoServiceImplements {

    @Autowired
    private RiesgoRepository riesgoRepository;

    public List<Riesgo> listarRiesgos() {
        return riesgoRepository.findAll();
    }

    public Riesgo buscarRiesgo(Integer id) {
        return riesgoRepository.findById(id).orElse(null);
    }

    public Riesgo crearRiesgo(Riesgo riesgo) {
        // Como es nuevo, usamos el save directo
        return riesgoRepository.save(riesgo);
    }

    public Riesgo actualizarRiesgo(Integer id, Riesgo riesgo) {
        return riesgoRepository.findById(id).map(riesgoExistente -> {
            // Mapeamos los datos nuevos al registro que ya existe
            riesgoExistente.setNivelRiesgo(riesgo.getNivelRiesgo());
            riesgoExistente.setDescripcionRiesgo(riesgo.getDescripcionRiesgo());
            riesgoExistente.setFkIdEstudiante(riesgo.getFkIdEstudiante());
            return riesgoRepository.save(riesgoExistente);
        }).orElseThrow(() -> new RuntimeException("No se encontró el riesgo con ID: " + id));
    }

    public void eliminarRiesgo(Integer id) {
        riesgoRepository.deleteById(id);
    }
}