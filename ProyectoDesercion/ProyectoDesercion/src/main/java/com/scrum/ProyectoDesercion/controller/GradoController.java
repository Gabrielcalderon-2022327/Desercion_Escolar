package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Grado;
import com.scrum.ProyectoDesercion.service.GradoService;
import com.scrum.ProyectoDesercion.service.MaestroService;
import com.scrum.ProyectoDesercion.validator.GradoValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GradoController {

    @Autowired
    private GradoService service;

    @Autowired
    private GradoValidator validator;

    @Autowired
    private MaestroService serviceM;

    @GetMapping("/grado")
    public String cargarGrados(Model model, HttpSession session){
        if(session.getAttribute("username") == null){
            return "redirect:/login";
        } else{
            model.addAttribute("username",  session.getAttribute("username"));
        }
        if(!model.containsAttribute("grados")){
            model.addAttribute("grados", service.getAllGrado());
        }
        // Enviamos los maestros para el popup
        model.addAttribute("maestros", serviceM.getAllMaestros());
        return "Grado";
    }

    @GetMapping("/grado/listar")
    public String listarGrados(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("grados", service.getAllGrado());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/grado";
    }

    @GetMapping("/grado/buscar")
    public String buscarGrado(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario){
        Grado grado = service.getGradoById(idUsuario);
        redirectAttributes.addFlashAttribute("grados", List.of(grado));
        redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        return "redirect:/grado";
    }

    @PostMapping("/grado/crear")
    public String crearGrado(RedirectAttributes redirectAttributes,
                             @Valid @RequestParam String nombre_grado,
                             @Valid @RequestParam Integer fk_id_maestro){

        Grado newGrado = new Grado();
        newGrado.setNombre_grado(nombre_grado);
        newGrado.setFk_id_maestro(fk_id_maestro);

        validator.validar(newGrado);
        service.saveGrado(newGrado);

        redirectAttributes.addFlashAttribute("success", "Se creó un nuevo registro!");
        return "redirect:/grado";
    }

    @PostMapping("/grado/editar")
    public String editarGrado(RedirectAttributes redirectAttributes,
                              @RequestParam("id_grado") Integer id_grado,
                              @RequestParam("nombre_grado") String nombre_grado,
                              @RequestParam("fk_id_maestro") Integer fk_id_maestro){

        Grado editGrado = new Grado();
        editGrado.setNombre_grado(nombre_grado);
        editGrado.setFk_id_maestro(fk_id_maestro);

        validator.validar(editGrado);
        service.updateGrado(id_grado, editGrado);

        redirectAttributes.addFlashAttribute("success", "Se editó el registro correctamente!");
        return "redirect:/grado";
    }

    @GetMapping("/grado/eliminar/{id}")
    public String eliminarGrado(RedirectAttributes redirectAttributes, @PathVariable Integer id){
        service.deleteGrado(id);
        redirectAttributes.addFlashAttribute("success", "Se eliminó el registro!");
        return "redirect:/grado";
    }
}