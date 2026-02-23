package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Economia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EconomiaService {
    List<Economia> getAllEconomias();
    Economia getEconomiaById(Integer id);
    Economia saveEconomia (Economia economia) throws RuntimeException;
    Economia updateEconomia(Integer id, Economia economia);
    void deleteEconomia(Integer id);
}
