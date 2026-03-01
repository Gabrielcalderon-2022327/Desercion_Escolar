package com.scrum.ProyectoDesercion.service;

import com.scrum.ProyectoDesercion.entity.Riesgo;
import java.util.List;

public interface RiesgoService {

    List<Riesgo> listarRiesgos();
    Riesgo buscarRiesgo(Integer id);
    Riesgo crearRiesgo(Riesgo riesgo);
    Riesgo actualizarRiesgo(Integer id, Riesgo riesgo);
    void eliminarRiesgo(Integer id);

}