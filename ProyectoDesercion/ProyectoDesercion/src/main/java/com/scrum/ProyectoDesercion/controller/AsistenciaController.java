package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Asistencia;
import com.scrum.ProyectoDesercion.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    // Cargar vista principal
    @GetMapping("/asistencias")
    public String cargarAsistencias(Model model) {
        if (!model.containsAttribute("asistencias")) {
            model.addAttribute("asistencias", asistenciaService.getAllAsistencia());
        }
        return "Asistencia";
    }

    // Listar asistencias
    @GetMapping("/asistencias/listar")
    public String listarAsistencias(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("asistencias", asistenciaService.getAllAsistencia());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/asistencias";
    }

    // Buscar asistencia
    @GetMapping("/asistencias/buscar")
    public String buscarAsistencia(@RequestParam Integer idAsistencia,
                                   RedirectAttributes redirectAttributes) {
        try {
            Asistencia asistencia = asistenciaService.getAsistenciaById(idAsistencia);
            redirectAttributes.addFlashAttribute("asistencias", List.of(asistencia));
            redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el registro: " + e.getMessage());
        }
        return "redirect:/asistencias";
    }

    // Crear asistencia
    @PostMapping("/asistencias/crear")
    public String crearAsistencia(@Valid @RequestParam LocalDate fecha_asistencia,
                                  @Valid @RequestParam String estado_asistencia,
                                  @Valid @RequestParam Integer fk_id_estudiante,
                                  RedirectAttributes redirectAttributes) {
        try {
            Asistencia asistencia = new Asistencia();
            asistencia.setFecha_asistencia(fecha_asistencia);
            asistencia.setEstado_asistencia(estado_asistencia);
            asistencia.setFk_id_estudiante(fk_id_estudiante);
            asistenciaService.saveAsistencia(asistencia);
            redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el registro: " + e.getMessage());
        }
        return "redirect:/asistencias";
    }

    // Editar asistencia
    @PostMapping("/asistencias/editar")
    public String editarAsistencia(@Valid @RequestParam Integer id_asistencia,
                                   @Valid @RequestParam LocalDate fecha_asistencia,
                                   @Valid @RequestParam String estado_asistencia,
                                   @Valid @RequestParam Integer fk_id_estudiante,
                                   RedirectAttributes redirectAttributes) {
        try {
            Asistencia asistencia = new Asistencia();
            asistencia.setFecha_asistencia(fecha_asistencia);
            asistencia.setEstado_asistencia(estado_asistencia);
            asistencia.setFk_id_estudiante(fk_id_estudiante);
            asistenciaService.updateAsistencia(id_asistencia, asistencia);
            redirectAttributes.addFlashAttribute("success", "Se actualizó el registro no: " + id_asistencia + "!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el registro: " + e.getMessage());
        }
        return "redirect:/asistencias";
    }

    // Eliminar asistencia
    @GetMapping("/asistencias/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable Integer id,
                                     RedirectAttributes redirectAttributes) {
        try {
            asistenciaService.deleteAsistencia(id);
            redirectAttributes.addFlashAttribute("success", "Se eliminó el registro correctamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el registro: " + e.getMessage());
        }
        return "redirect:/asistencias";
    }
}