package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Alerta;
import com.scrum.ProyectoDesercion.service.AlertaService;
import com.scrum.ProyectoDesercion.validator.AlertaValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AlertaController {

    @Autowired
    private AlertaService alertaService;
    @Autowired
    private AlertaValidator alertaValidator;

    @GetMapping("/alertas")
    public String cargarAlertas(Model model){
        if(!model.containsAttribute("alertas")){
            model.addAttribute("alertas", alertaService.getAllAlerta());
        }
        return "Alerta";
    }

    @GetMapping("/alertas/listar")
    public String listarAlertas(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("alertas", alertaService.getAllAlerta());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/alertas";
    }

    @GetMapping("/alertas/buscar")
    public String buscarAlertas(RedirectAttributes redirectAttributes, @RequestParam Integer idAlerta){
        Alerta alerta = alertaService.getAlertaById(idAlerta);
        redirectAttributes.addFlashAttribute("alertas", List.of(alerta));
        redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        return "redirect:/alertas";
    }

    @PostMapping("/alertas/crear")
    public String crearAlerta(RedirectAttributes redirectAttributes,
                              @Valid @RequestParam LocalDate fecha_alerta,
                              @Valid @RequestParam String tipo_alerta,
                              @Valid @RequestParam String incidente_alerta,
                              @Valid @RequestParam Integer fk_id_riesgo){
        Alerta newAlerta = new Alerta();
        newAlerta.setFecha_alerta(fecha_alerta);
        newAlerta.setTipo_alerta(tipo_alerta);
        newAlerta.setIncidente_alerta(incidente_alerta);
        newAlerta.setFk_id_riesgo(fk_id_riesgo);
        alertaValidator.validar(newAlerta);
        alertaService.saveAlerta(newAlerta);
        redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        return "redirect:/alertas";
    }

    @PostMapping("/alertas/editar")
    public String editarAlerta(RedirectAttributes redirectAttributes,
                                @Valid @RequestParam Integer id_alerta,
                                @Valid @RequestParam LocalDate fecha_alerta,
                                @Valid @RequestParam String tipo_alerta,
                                @Valid @RequestParam String incidente_alerta,
                                @Valid @RequestParam Integer fk_id_riesgo){
        Alerta newAlerta = new Alerta();
        newAlerta.setFecha_alerta(fecha_alerta);
        newAlerta.setTipo_alerta(tipo_alerta);
        newAlerta.setIncidente_alerta(incidente_alerta);
        newAlerta.setFk_id_riesgo(fk_id_riesgo);
        alertaService.updateAlerta(id_alerta, newAlerta);
        redirectAttributes.addFlashAttribute("success", "Se editó el registro no: " + id_alerta + "!");
        return "redirect:/alertas";
    }

    @GetMapping("/alertas/eliminar/{id}")
    public String eliminarAlerta(RedirectAttributes redirectAttributes, @PathVariable Integer id){
        alertaService.deleteAlerta(id);
        redirectAttributes.addFlashAttribute("success", "Se eliminó el registro!");
        return "redirect:/alertas";
    }


}