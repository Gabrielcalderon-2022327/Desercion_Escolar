package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Economia;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.EconomiaRepository;

import java.util.List;

public class EconomiaServiceImplements implements EconomiaService {
    private final EconomiaRepository economiaRepository;

    public EconomiaServiceImplements(EconomiaRepository economiaRepository){
        this.economiaRepository = economiaRepository;
    }


    @Override
    public List<Economia> getAllEconomias() {
        return economiaRepository.findAll();
    }

    @Override
    public Economia getEconomiaById(Integer id) {
        Economia economia = economiaRepository.findById(id).orElse(null);
        if (economia == null) {
            throw new ResourceNotFoundException("Economia No Encontrada");
        }
        return economia;
    }

    @Override
    public Economia saveEconomia(Economia economia) throws RuntimeException {
        return economiaRepository.save(economia);
    }

    public Economia updateEconomia(Integer id, Economia economia) {
        Economia updateEconomia = economiaRepository.findById(id).orElse(null);
        if (updateEconomia != null) {
            updateEconomia.setIngresosEconomia(economia.getIngresosEconomia());
            updateEconomia.setFechaEconomia(economia.getFechaEconomia());
            updateEconomia.setIdEstudiante(economia.getIdEstudiante());
        } else{
            throw new ResourceNotFoundException("Economia no encontrada");
        }
        return economiaRepository.save(updateEconomia);
    }

    @Override
    public void deleteEconomia(Integer id) {
        Economia asistencia = economiaRepository.findById(id).orElse(null);
        if (asistencia == null) {
            throw new ResourceNotFoundException("Economia no encontrada");
        }
        economiaRepository.delete(asistencia);
    }
}
