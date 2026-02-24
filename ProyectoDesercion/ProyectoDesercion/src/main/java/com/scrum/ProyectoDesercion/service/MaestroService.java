package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Maestro;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MaestroService {
    List<Maestro> getAllMaestros();
    Maestro getMaestroById(Integer id);
    Maestro saveMaestro (Maestro maestro) throws RuntimeException;
    Maestro updateMaestro(Integer id, Maestro maestro);
    void deleteMaestro(Integer id);
}
