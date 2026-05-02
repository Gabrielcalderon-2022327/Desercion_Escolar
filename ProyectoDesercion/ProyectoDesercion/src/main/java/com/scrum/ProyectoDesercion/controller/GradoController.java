package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Grado;
import com.scrum.ProyectoDesercion.service.GradoService;
import com.scrum.ProyectoDesercion.validator.GradoValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class GradoController {

    private final GradoService service;
    private final GradoValidator validator;

    public GradoController(GradoService service, GradoValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/grado")
    public String cargarGrados(Model model) {
        if (!model.containsAttribute("grados")) {
            model.addAttribute("grados", service.getAllGrado());
        }
        return "Grado";
    }

    @GetMapping("/grado/listar")
    public String listarGrados(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("grados", service.getAllGrado());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/grado";
    }

    @GetMapping("/grado/buscar")
    public String buscarGrado(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario) {
        Grado grado = service.getGradoById(idUsuario);
        redirectAttributes.addFlashAttribute("grados", List.of(grado));
        redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        return "redirect:/grado";
    }

    @PostMapping("/grado/crear")
    public String crearGrado(RedirectAttributes redirectAttributes,
            @Valid @RequestParam String nombre_grado,
            @Valid @RequestParam Integer fk_id_maestro) {

        Grado nuevoGrado = new Grado();
        nuevoGrado.setNombre_grado(nombre_grado);
        nuevoGrado.setFk_id_maestro(fk_id_maestro);
        validator.validar(nuevoGrado);
        service.saveGrado(nuevoGrado);
        redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        return "redirect:/grado";
    }


    @PostMapping("/grado/editar")
    public String editarGrado(RedirectAttributes redirectAttributes,
            @Valid @RequestParam Integer id_grado,
            @Valid @RequestParam String nombre_grado,
            @Valid @RequestParam Integer fk_id_maestro) {

        Grado nuevoGrado = new Grado();
        nuevoGrado.setId_grado(id_grado);
        nuevoGrado.setNombre_grado(nombre_grado);
        nuevoGrado.setFk_id_maestro(fk_id_maestro);

        validator.validar(nuevoGrado);
        service.updateGrado(id_grado, nuevoGrado);
        redirectAttributes.addFlashAttribute("success", "Se editó el registro no: " + id_grado + "!");
        return "redirect:/grado";
    }


    @GetMapping("/grados/eliminar/{id}")
    public String eliminarGrado(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.deleteGrado(id);
        redirectAttributes.addFlashAttribute("success", "Se eliminó el registro!");
        return "redirect:/grado";
    }
}