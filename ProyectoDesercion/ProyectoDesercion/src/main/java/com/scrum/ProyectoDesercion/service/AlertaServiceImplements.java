package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Alerta;
import com.scrum.ProyectoDesercion.exception.ResourceNotFoundException;
import com.scrum.ProyectoDesercion.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaServiceImplements implements AlertaService {

    private final AlertaRepository alertaRepository;

    public AlertaServiceImplements(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Override
    public List<Alerta> getAllAlerta() {
        return alertaRepository.findAll();
    }

    @Override
    public Alerta getAlertaById(Integer id) {
        Alerta alerta = alertaRepository.findById(id).orElse(null);
        if (alerta == null) {
            throw new ResourceNotFoundException("Alerta no encontrada");
        }
        return alerta;
    }

    @Override
    public Alerta saveAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    public Alerta updateAlerta(Integer id, Alerta alerta) {
        Alerta alerta1 = alertaRepository.findById(id).orElse(null);
        if (alerta1 == null) {
            throw new ResourceNotFoundException("Alerta no encontrada");
        } else {
            alerta1.setFecha_alerta(alerta.getFecha_alerta());
            alerta1.setIncidente_alerta(alerta.getIncidente_alerta());
            alerta1.setTipo_alerta(alerta.getTipo_alerta());
            alerta1.setFk_id_riesgo(alerta.getFk_id_riesgo());
        }
        return alertaRepository.save(alerta1);
    }

    @Override
    public void deleteAlerta(Integer id) {
        Alerta alerta = alertaRepository.findById(id).orElse(null);
        if (alerta == null) {
            throw new ResourceNotFoundException("Alerta no encontrada");
        }
        alertaRepository.delete(alerta);
    }
}