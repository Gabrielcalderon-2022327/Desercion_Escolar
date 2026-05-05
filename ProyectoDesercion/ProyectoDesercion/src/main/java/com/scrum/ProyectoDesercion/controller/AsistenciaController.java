package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.entity.Estudiante;
import com.scrum.ProyectoDesercion.service.AsistenciaService;
import com.scrum.ProyectoDesercion.service.EstudianteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class AsistenciaController {
    @Autowired
    private EstudianteService estudianteService;

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    // Cargar vista principal
    @GetMapping("/asistencia")
    public String cargarAsistencias(HttpSession session, Model model) {
        if(session.getAttribute("username") == null){
            return "redirect:/login";
        } else{
            model.addAttribute("username",  session.getAttribute("username"));
        }
        return "Asistencia";
    }

    // Buscar asistencias
    @GetMapping("/asistencia/listar")
    public String listarAsistencias(RedirectAttributes redirectAttributes,
                                    @RequestParam LocalDate fecha_asistencia,
                                    @RequestParam Integer id_seccion) {
        List<Asistencia> asistencias;
        asistencias = asistenciaService.getByFechaGrado(fecha_asistencia, id_seccion);
        if(asistencias.isEmpty()){
            List<Estudiante> estudiantes = estudianteService.getEstudiantesByGrupo(id_seccion);

            for(Estudiante e : estudiantes){
                Asistencia a = new Asistencia();
                a.setFecha_asistencia(fecha_asistencia);
                a.setFk_id_estudiante(e.getId_estudiante());
                a.setEstudiante(e);
                a.setEstado_asistencia("presente");
                asistencias.add(a);
            }
        }
        redirectAttributes.addAttribute("asistencias", asistencias);
        redirectAttributes.addAttribute("id_seccion", id_seccion);
        redirectAttributes.addFlashAttribute("success", "Se busco la tabla correctamente!");
        return "redirect:/asistencia";
    }

    // Guardar lista de asistencias
    @PostMapping("/asistencia/guardar")
    public String guardarAsistencias(@RequestParam LocalDate fecha_asistencia1,
                                     @RequestParam Map<String, String> allParams,
                                     RedirectAttributes redirectAttributes) {
        try {
            List<Asistencia> asistencias = new java.util.ArrayList<>();
            int i = 0;
            while (allParams.containsKey("estudiante_" + i)) {
                Integer idEstudiante = Integer.parseInt(allParams.get("estudiante_" + i));
                String estado = allParams.getOrDefault("estado_" + i, "presente");
                Asistencia a = new Asistencia();
                a.setFecha_asistencia(fecha_asistencia1);
                a.setFk_id_estudiante(idEstudiante);
                a.setEstado_asistencia(estado);
                asistencias.add(a);
                i++;
            }
            asistenciaService.saveAll(asistencias);
            redirectAttributes.addFlashAttribute("success", "¡Asistencia guardada correctamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/asistencia";
    }
}