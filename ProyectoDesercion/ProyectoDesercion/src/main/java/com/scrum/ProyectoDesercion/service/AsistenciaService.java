package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.Asistencia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AsistenciaService {
    List<Asistencia> getAllAsistencia();
    Asistencia getAsistenciaById(Integer id);
    Asistencia saveAsistencia(Asistencia asistencia) throws RuntimeException;
    Asistencia updateAsistencia(Integer id, Asistencia asistencia);
    void deleteAsistencia(Integer id);
}
