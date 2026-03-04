package com.scrum.ProyectoDesercion.service;


import com.scrum.ProyectoDesercion.entity.Alerta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlertaService {

List<Alerta>getAllAlerta();
Alerta getAlertaById(Integer id);
Alerta saveAlerta (Alerta alerta) throws RuntimeException;
Alerta updateAlerta (Integer id, Alerta alerta);
void deleteAlerta (Integer id);

}
