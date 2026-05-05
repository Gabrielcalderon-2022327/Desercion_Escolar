package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Riesgo;
import com.scrum.ProyectoDesercion.service.RiesgoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RiesgoController {

    private final RiesgoService riesgoService;

    public RiesgoController(RiesgoService riesgoService) {
        this.riesgoService = riesgoService;
    }

    // Cargar vista principal
    @GetMapping("/riesgos")
    public String cargarRiesgos(Model model, HttpSession session){
        if (session.getAttribute("username") == null){
            return "redirect:/login";
        } else{
            model.addAttribute("username", session.getAttribute("username"));
        }
        if(!model.containsAttribute("riesgos")){
            model.addAttribute("riesgos", riesgoService.listarRiesgos());
        }
        return "Riesgo";
    }

    // Listar riesgos
    @GetMapping("riesgos/listar")
    public String listarRiesgos(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("riesgos", riesgoService.listarRiesgos());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/riesgos";
    }

    // Buscar riesgo
    @GetMapping("/riesgos/buscar")
    public String buscarRiesgo(@RequestParam Integer id_riesgo,
                               RedirectAttributes redirectAttributes) {
        try {
            Riesgo riesgo = riesgoService.buscarRiesgo(id_riesgo);
            redirectAttributes.addFlashAttribute("riesgos", List.of(riesgo));
            redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el registro: " + e.getMessage());
        }
        return "redirect:/riesgos";
    }

    @PostMapping("/riesgos/crear")
    public String crearRiesgo(RedirectAttributes redirectAttributes,
                              @Valid @RequestParam String descripcion_riesgo,
                              @Valid @RequestParam String nivel_riesgo,
                              @Valid @RequestParam Integer fk_id_estudiante) {
        try {
            Riesgo riesgo = new Riesgo();
            riesgo.setDescripcion_riesgo(descripcion_riesgo);
            riesgo.setNivel_riesgo(nivel_riesgo);
            riesgo.setFk_id_estudiante(fk_id_estudiante);
            riesgoService.crearRiesgo(riesgo);
            redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el registro: " + e.getMessage());
        }
        return "redirect:/riesgos";
    }

    @PostMapping("/riesgos/editar")
    public String editarRiesgo(RedirectAttributes redirectAttributes,
                               @Valid @RequestParam Integer id_riesgo,
                               @Valid @RequestParam String descripcion_riesgo,
                               @Valid @RequestParam String nivel_riesgo,
                               @Valid @RequestParam Integer fk_id_estudiante) {
        try {
            Riesgo riesgo = new Riesgo();
            riesgo.setDescripcion_riesgo(descripcion_riesgo);
            riesgo.setNivel_riesgo(nivel_riesgo);
            riesgo.setFk_id_estudiante(fk_id_estudiante);
            riesgoService.actualizarRiesgo(id_riesgo, riesgo);
            redirectAttributes.addFlashAttribute("success", "Se actualizó el registro no: " + id_riesgo + "!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el registro: " + e.getMessage());
        }
        return "redirect:/riesgos";
    }

    @GetMapping("/riesgos/eliminar/{id}")
    public String eliminarRiesgo(@PathVariable Integer id,
                                 RedirectAttributes redirectAttributes) {
        try {
            riesgoService.eliminarRiesgo(id);
            redirectAttributes.addFlashAttribute("success", "Se eliminó el registro correctamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el registro: " + e.getMessage());
        }
        return "redirect:/riesgos";
    }
}