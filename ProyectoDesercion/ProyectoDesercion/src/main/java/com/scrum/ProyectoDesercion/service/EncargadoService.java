package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Encargado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EncargadoService {
    List<Encargado> getAllEncargado();
    Encargado getEncargadoById(Integer id);
    Encargado saveEncargado(Encargado encargado) throws RuntimeException;
    Encargado updateEncargado(Integer id, Encargado encargado);
    void deleteEncargado(Integer id);
}
