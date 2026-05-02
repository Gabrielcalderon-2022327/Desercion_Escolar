package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Maestro;
import com.scrum.ProyectoDesercion.service.MaestroService;
import com.scrum.ProyectoDesercion.validator.MaestroValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MaestroController {

    private final MaestroService service;
    private final MaestroValidator validator;

    public MaestroController(MaestroService service, MaestroValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/maestros")
    public String cargarMaestros(Model model) {
        if (!model.containsAttribute("maestros")) {
            model.addAttribute("maestros", service.getAllMaestros());
        }
        return "Maestro";
    }

    @GetMapping("/maestros/listar")
    public String listarMaestros(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("maestros", service.getAllMaestros());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/maestros";
    }

    @GetMapping("/maestros/buscar")
    public String buscarMaestro(RedirectAttributes redirectAttributes, @RequestParam Integer idMaestro) {
        Maestro maestro = service.getMaestroById(idMaestro);
        redirectAttributes.addFlashAttribute("maestros", List.of(maestro));
        redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        return "redirect:/maestros";
    }

    @PostMapping("/maestros/crear")
    public String crearMaestro( RedirectAttributes redirectAttributes,
            @Valid @RequestParam String nombre_maestro,
            @Valid @RequestParam String especialidad_maestro,
            @Valid @RequestParam Integer telefono_maestro,
            @Valid @RequestParam Integer fk_id_usuario) {

        Maestro nuevoMaestro = new Maestro();
        nuevoMaestro.setNombreMaestro(nombre_maestro);
        nuevoMaestro.setEspecialidadMaestro(especialidad_maestro);
        nuevoMaestro.setTelefonoMaestro(telefono_maestro);
        nuevoMaestro.setIdUsuario(fk_id_usuario);
        validator.validar(nuevoMaestro);
        service.saveMaestro(nuevoMaestro);
        redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        return "redirect:/maestros";
    }

    @PostMapping("/maestros/editar")
    public String editarMaestro( RedirectAttributes redirectAttributes,
            @Valid @RequestParam Integer id_maestro,
            @Valid @RequestParam String nombre_maestro,
            @Valid @RequestParam String especialidad_maestro,
            @Valid @RequestParam Integer telefono_maestro,
            @Valid @RequestParam Integer fk_id_usuario) {

        Maestro nuevoMaestro = new Maestro();
        nuevoMaestro.setNombreMaestro(nombre_maestro);
        nuevoMaestro.setEspecialidadMaestro(especialidad_maestro);
        nuevoMaestro.setTelefonoMaestro(telefono_maestro);
        nuevoMaestro.setIdUsuario(fk_id_usuario);

        validator.validarUpdate(nuevoMaestro, id_maestro);
        service.updateMaestro(id_maestro, nuevoMaestro);
        redirectAttributes.addFlashAttribute("success", "Se editó el registro no: " + id_maestro + "!");
        return "redirect:/maestros";
    }

    @GetMapping("/maestros/eliminar/{id}")
    public String eliminarMaestro(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.deleteMaestro(id);
        redirectAttributes.addFlashAttribute("success", "Se eliminó el registro!");
        return "redirect:/maestros";
    }
}