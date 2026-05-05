package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Alerta;
import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.service.AlertaService;
import com.scrum.ProyectoDesercion.service.AsistenciaService;
import com.scrum.ProyectoDesercion.service.EstudianteService;
import com.scrum.ProyectoDesercion.service.RiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private RiesgoService riesgoService;
    @Autowired
    private AsistenciaService asistenciaService;
    @Autowired
    private AlertaService alertaService;

    @GetMapping("/index")
    public String cargarIndex(Model model){
        model.addAttribute("totalEstudiantes", estudianteService.getAllEstudiantes().size());
        model.addAttribute("riesgoAlto", getRiesgoAlto());
        model.addAttribute("asistenciaPromedio", getAsistencia());
        model.addAttribute("alertas", alertaPorFecha());
        return "Pagina principal";
    }


    private int getRiesgoAlto() {
        int riesgoAlto = 0;
        if (riesgoService.listarRiesgos().isEmpty()) {
            return riesgoAlto;
        }
        for (Riesgo r : riesgoService.listarRiesgos()) {
            if (r.getNivel_riesgo().equalsIgnoreCase("alto")) {
                riesgoAlto++;
            }
        }
        return riesgoAlto;
    }

    private int getAsistencia(){
        double totalAsistencia = asistenciaService.getAllAsistencia().size();
        double totalPresente = 0;
        for (Asistencia a : asistenciaService.getAllAsistencia()){
            if(a.getEstado_asistencia().equalsIgnoreCase("presente") || a.getEstado_asistencia().equalsIgnoreCase("tardanza")){
                totalPresente ++;
            }
        }
        double promedio = totalPresente /totalAsistencia * 100;
        return (int) Math.round(promedio);
    }

    private List<Alerta> alertaPorFecha(){
        int limiteAlertas = 10;
        List<Alerta> alertas = alertaService.getAllAlerta();
        alertas.sort(Comparator.comparing(Alerta::getFecha_alerta).reversed());
        if (alertas.size() < 10){
            limiteAlertas = alertas.size();
        }
        if (alertas.size() == 0){
            return alertas;
        }
        alertas = alertas.subList(0,limiteAlertas);
        return alertas;
    }

}
