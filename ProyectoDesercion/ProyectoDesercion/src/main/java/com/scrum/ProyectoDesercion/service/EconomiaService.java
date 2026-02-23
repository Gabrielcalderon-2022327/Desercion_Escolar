package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Economia;

import java.util.List;

public interface EconomiaService {
    List<Economia> getAllEconomias();
    Economia getEconomiaById(Integer id);
    Economia saveEconomia (Economia economia) throws RuntimeException;
    Economia updateEconomia(Integer id, Economia economia);
    void deleteEconomia(Integer id);
}
