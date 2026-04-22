package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Economia;
import com.scrum.ProyectoDesercion.service.EconomiaService;
import com.scrum.ProyectoDesercion.validator.EconomiaValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EconomiaController {

    @Autowired
    private EconomiaService economiaService;

    @Autowired
    private EconomiaValidator economiaValidator;

    @GetMapping("/economia")
    public String cargarEconomia(Model model){
        // Verificamos si ya existe el atributo para no sobreescribirlo tras un redirect
        if(!model.containsAttribute("economia")){
            model.addAttribute("economia", economiaService.getAllEconomias());
        }
        return "Economia"; // Asegúrate de que tu archivo HTML se llame exactamente Economia.html
    }

    @GetMapping("/economia/listar")
    public String listarEconomia(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("economia", economiaService.getAllEconomias());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/economia";
    }

    @GetMapping("/economia/buscar")
    public String buscarEconomia(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario){
        // Mantenemos idUsuario porque es el name que le pusiste al input en tu HTML
        Economia economia = economiaService.getEconomiaById(idUsuario);
        redirectAttributes.addFlashAttribute("economia", List.of(economia));
        redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        return "redirect:/economia";
    }

    @PostMapping("/economia/crear")
    public String crearEconomia(RedirectAttributes redirectAttributes,
                                @Valid @RequestParam Double ingresosEconomia,
                                @Valid @RequestParam LocalDate fechaEconomia,
                                @Valid @RequestParam Integer idEstudiante){

        Economia newEconomia = new Economia();
        newEconomia.setIngresosEconomia(ingresosEconomia);
        newEconomia.setFechaEconomia(fechaEconomia);
        newEconomia.setIdEstudiante(idEstudiante);

        economiaService.saveEconomia(newEconomia);

        redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        return "redirect:/economia";
    }

    @PostMapping("/economia/editar")
    public String editarEconomia(RedirectAttributes redirectAttributes,
                                 @RequestParam("idEconomia") Integer idEconomia,
                                 @RequestParam("ingresosEconomia") Double ingresosEconomia,
                                 @RequestParam("fechaEconomia") String fechaEconomia,
                                 @RequestParam("idEstudiante") Integer idEstudiante){

        Economia editEconomia = new Economia();
        editEconomia.setIngresosEconomia(ingresosEconomia);
        editEconomia.setFechaEconomia(LocalDate.parse(fechaEconomia));
        editEconomia.setIdEstudiante(idEstudiante);

        economiaValidator.validar(editEconomia);
        economiaService.updateEconomia(idEconomia, editEconomia);

        redirectAttributes.addFlashAttribute("success", "Se editó el registro correctamente!");
        return "redirect:/economia";
    }

    @GetMapping("/economia/eliminar/{id}")
    public String eliminarEconomia(RedirectAttributes redirectAttributes, @PathVariable Integer id){
        economiaService.deleteEconomia(id);
        redirectAttributes.addFlashAttribute("success", "Se eliminó el registro!");
        return "redirect:/economia";
    }
}