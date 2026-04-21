package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Estudiante;
import com.scrum.ProyectoDesercion.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @GetMapping("/estudiantes")
    public String cargarEstudiantes(Model model) {
        if (!model.containsAttribute("estudiantes")) {
            model.addAttribute("estudiantes", service.getAllEstudiantes());
        }
        return "Estudiantes"; // Tu HTML debe llamarse Estudiantes.html
    }

    @GetMapping("/estudiantes/listar")
    public String listarEstudiantes(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "estudiantes",
                service.getAllEstudiantes()
        );
        return "redirect:/estudiantes";
    }

    @GetMapping("/estudiantes/buscar")
    public String buscarEstudiante(
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "id_estudiante", required = false) Integer id) {

        if (id != null) {
            // Buscar estudiante por ID
            Estudiante estudiante = service.getEstudianteById(id).orElse(null);
            if (estudiante != null) {
                redirectAttributes.addFlashAttribute("estudiantes", List.of(estudiante));
            } else {
                redirectAttributes.addFlashAttribute("estudiantes", service.getAllEstudiantes());
            }
        } else {
            // Si no se pasa ID, listar todos
            redirectAttributes.addFlashAttribute("estudiantes", service.getAllEstudiantes());
        }

        return "redirect:/estudiantes";
    }

    @PostMapping("/estudiantes/crear")
    public String crearEstudiante(@ModelAttribute Estudiante estudiante,
                                  RedirectAttributes redirectAttributes) {
        service.saveEstudiante(estudiante);
        redirectAttributes.addFlashAttribute("success", "Estudiante creado correctamente");
        return "redirect:/estudiantes";
    }

    @PostMapping("/estudiantes/editar")
    public String editarEstudiante(@ModelAttribute Estudiante estudiante,
                                   RedirectAttributes redirectAttributes) {
        service.updateEstudiante(estudiante.getId_estudiante(), estudiante);
        redirectAttributes.addFlashAttribute("success", "Estudiante actualizado correctamente");
        return "redirect:/estudiantes";
    }

    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id,
                                     RedirectAttributes redirectAttributes) {
        service.deleteEstudiante(id);
        redirectAttributes.addFlashAttribute("success", "Estudiante eliminado correctamente");
        return "redirect:/estudiantes";
    }
}