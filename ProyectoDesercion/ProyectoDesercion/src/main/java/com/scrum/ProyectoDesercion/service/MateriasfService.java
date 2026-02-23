package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Materiasf;

import java.util.List;

public interface MateriasfService {
    List<Materiasf> getAllMateriasf();
    Materiasf getEconomiaById(Integer id);
    Materiasf saveEconomia (Materiasf materiasf) throws RuntimeException;
    Materiasf updateEconomia(Integer id, Materiasf materiasf);
    void deleteMateriasf(Integer id);
}
