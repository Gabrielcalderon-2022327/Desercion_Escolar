package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Estudiante;
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

    //Carga la vista principal con la lista de Materias Perdidas
    @GetMapping("/materiasf")
    public String cargarMaterias(Model model){
        if(!model.containsAttribute("materiasf")){
            model.addAttribute("materiasf", materiasFService.getAllMateriasF());
        }
        return "MateriasF";
    }

    //Lista todos los datos
    @GetMapping("/materiasf/listar")
    public String listarMaterias(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(
                "materiasf",
                materiasFService.getAllMateriasF()
        );
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/materiasf";
    }

    //Busca una Materia por su ID
    @GetMapping("/materiasf/buscar")
    public String buscarMaterias(RedirectAttributes redirectAttributes,
                                 @RequestParam Integer id){
        MateriasF materia = materiasFService.getMateriasFById(id)
                .orElse(null);

        if (materia != null) {
            redirectAttributes.addFlashAttribute("materiasf", List.of(materia));
            redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        } else {
            redirectAttributes.addFlashAttribute(
                    "materiasf",
                    materiasFService.getAllMateriasF()
            );
        }
        return "redirect:/materiasf";
    }

    //Crear Materia
    @PostMapping("/materiasf/crear")
    public String crearEstudiante(@ModelAttribute MateriasF materiasF,
                                  RedirectAttributes redirectAttributes) {
        materiasFService.saveMateriasF(materiasF);
        redirectAttributes.addFlashAttribute("success", "Materia creada correctamente");
        return "redirect:/materiasf";
    }

    //Editar Materia
    @PostMapping("/materiasf/editar")
    public String editarMateria(@Valid @ModelAttribute MateriasF materia, RedirectAttributes redirectAttributes){
        materiasFService.updateMateriasF(
                materia.getIdMateriasF(),
                materia
        );
        redirectAttributes.addFlashAttribute("success", "Materia actualizada correctamente");
        return "redirect:/materiasf";
    }

    //Eliminar Materia por su ID
    @GetMapping("/materiasf/eliminar/{id}")
    public String eliminarMateria(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        materiasFService.deleteMateriasF(id);
        redirectAttributes.addFlashAttribute("success", "Materia eliminada correctamente");
        return "redirect:/materiasf";
    }
}
