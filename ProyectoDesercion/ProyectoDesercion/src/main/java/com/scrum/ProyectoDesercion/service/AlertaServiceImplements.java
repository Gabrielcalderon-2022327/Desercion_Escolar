package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Alerta;
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
        return alertaRepository.findById(id).orElse(null);
    }

    @Override
    public Alerta saveAlerta(Alerta alerta) {
        // Este funciona simple porque es un registro nuevo
        return alertaRepository.save(alerta);
    }

    @Override
    public Alerta updateAlerta(Integer id, Alerta alerta) {
        return alertaRepository.findById(id).map(alertaExistente -> {
            alertaExistente.setFechaAlerta(alerta.getFechaAlerta());
            alertaExistente.setTipoAlerta(alerta.getTipoAlerta());
            alertaExistente.setIncidenteAlerta(alerta.getIncidenteAlerta());
            return alertaRepository.save(alertaExistente);
        }).orElseThrow(() -> new RuntimeException("No se encontró la alerta con ID: " + id));
    }

    @Override
    public void deleteAlerta(Integer id) {
        if (alertaRepository.existsById(id)) {
            alertaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar: Alerta no encontrada.");
        }
    }
}