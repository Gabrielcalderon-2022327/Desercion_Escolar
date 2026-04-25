package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Maestro;
import com.scrum.ProyectoDesercion.service.MaestroService;
import com.scrum.ProyectoDesercion.validator.MaestroValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/maestros")
public class MaestroController {

    private final MaestroService service;
    private final MaestroValidator validator;

    public MaestroController(MaestroService service, MaestroValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/")
    public String cargarMaestros(Model model) {
        if (!model.containsAttribute("maestros")) {
            model.addAttribute("maestros", service.getAllMaestros());
        }
        return "Maestro";
    }

    @GetMapping("/listar")
    public String listarMaestros() {
        return "redirect:/maestros/";
    }

    @GetMapping("/buscar")
    public String buscarMaestro(RedirectAttributes redirectAttributes,
                                @RequestParam(required = false) Integer idMaestro) {
        if (idMaestro == null) {
            return "redirect:/maestros/";
        }
        Maestro maestro = service.getMaestroById(idMaestro);
        if (maestro != null) {
            redirectAttributes.addFlashAttribute("maestros", List.of(maestro));
        }
        return "redirect:/maestros/";
    }

    @PostMapping("/crear")
    public String crearMaestro(
            @RequestParam String nombre_maestro,
            @RequestParam String especialidad_maestro,
            @RequestParam Integer telefono_maestro,
            @RequestParam Integer fk_id_usuario) {

        Maestro nuevoMaestro = new Maestro();
        // ESTOS SON LOS NOMBRES QUE TIENES EN TU ENTIDAD:
        nuevoMaestro.setNombreMaestro(nombre_maestro);
        nuevoMaestro.setEspecialidadMaestro(especialidad_maestro);
        nuevoMaestro.setTelefonoMaestro(telefono_maestro);
        nuevoMaestro.setIdUsuario(fk_id_usuario);

        validator.validar(nuevoMaestro);
        service.saveMaestro(nuevoMaestro);
        return "redirect:/maestros/";
    }

    @PostMapping("/editar")
    public String editarMaestro(
            @RequestParam Integer id_maestro,
            @RequestParam String nombre_maestro,
            @RequestParam String especialidad_maestro,
            @RequestParam Integer telefono_maestro,
            @RequestParam Integer fk_id_usuario) {

        Maestro nuevoMaestro = new Maestro();
        nuevoMaestro.setIdMaestro(id_maestro); // Según tu entidad es setIdMaestro
        nuevoMaestro.setNombreMaestro(nombre_maestro);
        nuevoMaestro.setEspecialidadMaestro(especialidad_maestro);
        nuevoMaestro.setTelefonoMaestro(telefono_maestro);
        nuevoMaestro.setIdUsuario(fk_id_usuario); // Según tu entidad es setIdUsuario

        validator.validar(nuevoMaestro);
        service.updateMaestro(id_maestro, nuevoMaestro);
        return "redirect:/maestros/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMaestro(@PathVariable Integer id) {
        service.deleteMaestro(id);
        return "redirect:/maestros/";
    }
}