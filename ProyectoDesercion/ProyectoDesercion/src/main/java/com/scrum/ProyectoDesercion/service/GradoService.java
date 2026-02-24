package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Grado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradoService {
    List<Grado> getAllGrado();
    Grado getGradoById(Integer id);
    Grado saveGrado(Grado grado) throws RuntimeException;
    Grado updateGrado(Integer id, Grado grado);
    Grado deleteGrado(Integer id);
}
