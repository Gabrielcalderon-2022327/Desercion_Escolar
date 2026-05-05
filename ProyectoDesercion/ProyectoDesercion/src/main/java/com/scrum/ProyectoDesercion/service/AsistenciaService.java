package com.scrum.ProyectoDesercion.service;
import com.scrum.ProyectoDesercion.entity.Asistencia;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AsistenciaService {
    List<Asistencia> getAllAsistencia();
    List<Asistencia> getByFechaGrado(LocalDate fechaAsistencia, Integer id_grado);
    void saveAll(List<Asistencia> asistencias);

}
