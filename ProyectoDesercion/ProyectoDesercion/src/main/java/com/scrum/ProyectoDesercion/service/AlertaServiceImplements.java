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
        return alertaRepository.save(alerta);
    }

    @Override
    public Alerta updateAlerta(Integer id, Alerta alerta) {
        Optional<Alerta> alertaExistente = alertaRepository.findById(id);
        if (alertaExistente.isPresent()) {
            Alerta alertaActualizada = alertaExistente.get();
            alertaActualizada.setFechaAlerta(alerta.getFechaAlerta());
            alertaActualizada.setTipoAlerta(alerta.getTipoAlerta());
            alertaActualizada.setIncidenteAlerta(alerta.getIncidenteAlerta());
            return alertaRepository.save(alertaActualizada);
        } else {
            throw new IllegalArgumentException("No se encontró la alerta con el ID: " + id);
        }
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