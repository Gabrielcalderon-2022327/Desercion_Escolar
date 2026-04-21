package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.MateriasF;
import com.scrum.ProyectoDesercion.service.MateriasfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MateriasfController {

    @Autowired
    private MateriasfService materiasFService;

    @GetMapping("/materiasf")
    public String cargarMaterias(Model model){
        if(!model.containsAttribute("materiasf")){
            model.addAttribute("materiasf", materiasFService.getAllMateriasF());
        }
        return "MateriasF"; // tu HTML debe llamarse MateriasF.html
    }

    @GetMapping("/materiasf/listar")
    public String listarMaterias(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(
                "materiasf",
                materiasFService.getAllMateriasF()
        );
        return "redirect:/materiasf";
    }

    @GetMapping("/materiasf/buscar")
    public String buscarMaterias(RedirectAttributes redirectAttributes,
                                 @RequestParam Integer id){
        MateriasF materia = materiasFService.getMateriasFById(id)
                .orElse(null);

        if (materia != null) {
            redirectAttributes.addFlashAttribute(
                    "materiasf",
                    List.of(materia)
            );
        } else {
            redirectAttributes.addFlashAttribute(
                    "materiasf",
                    materiasFService.getAllMateriasF()
            );
        }

        return "redirect:/materiasf";
    }

    @PostMapping("/materiasf/crear")
    public String crearMateria(@Valid @ModelAttribute MateriasF materia){
        materiasFService.saveMateriasF(materia);
        return "redirect:/materiasf";
    }

    @PostMapping("/materiasf/editar")
    public String editarMateria(@Valid @ModelAttribute MateriasF materia){
        materiasFService.updateMateriasF(
                materia.getIdMateriasF(),
                materia
        );
        return "redirect:/materiasf";
    }

    @GetMapping("/materiasf/eliminar/{id}")
    public String eliminarMateria(@PathVariable Integer id){
        materiasFService.deleteMateriasF(id);
        return "redirect:/materiasf";
    }
}
