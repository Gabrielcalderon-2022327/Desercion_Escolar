package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Grado;
import com.scrum.ProyectoDesercion.service.GradoService;
import com.scrum.ProyectoDesercion.validator.GradoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/grados")
public class GradoController {

    private final GradoService service;
    private final GradoValidator validator;

    public GradoController(GradoService service, GradoValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/")
    public String cargarGrados(Model model) {
        if (!model.containsAttribute("grados")) {
            model.addAttribute("grados", service.getAllGrado());
        }
        return "Grado";
    }

    @GetMapping("/buscar")
    public String buscarGrado(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario) {
        Grado grado = service.getGradoById(idUsuario);
        redirectAttributes.addFlashAttribute("grados", List.of(grado));
        return "redirect:/grados/";
    }


    @PostMapping("/crear")
    public String crearGrado(
            @RequestParam String nombre_grado,
            @RequestParam Integer fk_id_maestro) {

        Grado nuevoGrado = new Grado();
        nuevoGrado.setNombre_grado(nombre_grado);
        nuevoGrado.setFk_id_maestro(fk_id_maestro);

        validator.validar(nuevoGrado);
        service.saveGrado(nuevoGrado);
        return "redirect:/grados/";
    }


    @PostMapping("/editar")
    public String editarGrado(
            @RequestParam Integer id_grado,
            @RequestParam String nombre_grado,
            @RequestParam Integer fk_id_maestro) {

        Grado nuevoGrado = new Grado();
        nuevoGrado.setId_grado(id_grado);
        nuevoGrado.setNombre_grado(nombre_grado);
        nuevoGrado.setFk_id_maestro(fk_id_maestro);

        validator.validar(nuevoGrado);
        service.updateGrado(id_grado, nuevoGrado);
        return "redirect:/grados/";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarGrado(@PathVariable Integer id) {
        service.deleteGrado(id);
        return "redirect:/grados/";
    }
}