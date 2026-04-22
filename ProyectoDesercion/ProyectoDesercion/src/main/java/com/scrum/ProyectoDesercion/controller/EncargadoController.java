package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Encargado;
import com.scrum.ProyectoDesercion.service.EncargadoService;
import com.scrum.ProyectoDesercion.validator.EncargadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EncargadoController {

    @Autowired
    private EncargadoService encargadoService;

    @Autowired
    private EncargadoValidator encargadoValidator;

    @GetMapping("/encargado")
    public String cargarEncargado(Model model) {
        if (!model.containsAttribute("encargado")) {
            model.addAttribute("encargado", encargadoService.getAllEncargado());
        }
        return "Encargado"; // Asegúrate que el HTML se llame Encargado.html
    }

    @GetMapping("/encargado/listar")
    public String listarEncargado(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("encargado", encargadoService.getAllEncargado());
        redirectAttributes.addFlashAttribute("success", "Lista actualizada correctamente");
        return "redirect:/encargado";
    }

    @GetMapping("/encargado/buscar")
    public String buscarEncargado(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario) {
        Encargado encargado = encargadoService.getEncargadoById(idUsuario);
        redirectAttributes.addFlashAttribute("encargado", List.of(encargado));
        redirectAttributes.addFlashAttribute("success", "Encargado encontrado");
        return "redirect:/encargado";
    }

    @PostMapping("/encargado/crear")
    public String crearEncargado(RedirectAttributes redirectAttributes,
                                 @RequestParam String nombre_encargado,
                                 @RequestParam String apellido_encargado,
                                 @RequestParam String fecha_nacimiento_encargado,
                                 @RequestParam String direccion_encargado,
                                 @RequestParam String telefono_encargado) {

        Encargado newEncargado = new Encargado();
        newEncargado.setNombre_encargado(nombre_encargado);
        newEncargado.setApellido_encargado(apellido_encargado);
        newEncargado.setFecha_nacimiento_encargado(LocalDate.parse(fecha_nacimiento_encargado));
        newEncargado.setDireccion_encargado(direccion_encargado);
        newEncargado.setTelefono_encargado(Integer.parseInt(telefono_encargado));

        encargadoValidator.validar(newEncargado);
        encargadoService.saveEncargado(newEncargado);

        redirectAttributes.addFlashAttribute("success", "Registro creado exitosamente");
        return "redirect:/encargado";
    }

    @PostMapping("/encargado/editar")
    public String editarEncargado(RedirectAttributes redirectAttributes,
                                  @RequestParam Integer id_encargado,
                                  @RequestParam String nombre_encargado,
                                  @RequestParam String apellido_encargado,
                                  @RequestParam String fecha_nacimiento_encargado,
                                  @RequestParam String direccion_encargado,
                                  @RequestParam String telefono_encargado) {

        Encargado editEncargado = new Encargado();
        editEncargado.setNombre_encargado(nombre_encargado);
        editEncargado.setApellido_encargado(apellido_encargado);
        editEncargado.setFecha_nacimiento_encargado(LocalDate.parse(fecha_nacimiento_encargado));
        editEncargado.setDireccion_encargado(direccion_encargado);
        editEncargado.setTelefono_encargado(Integer.parseInt(telefono_encargado));

        encargadoValidator.validar(editEncargado);
        encargadoService.updateEncargado(id_encargado, editEncargado);

        redirectAttributes.addFlashAttribute("success", "Registro " + id_encargado + " actualizado");
        return "redirect:/encargado";
    }

    @GetMapping("/encargado/eliminar/{id}")
    public String eliminarEncargado(RedirectAttributes redirectAttributes, @PathVariable Integer id) {
        encargadoService.deleteEncargado(id);
        redirectAttributes.addFlashAttribute("success", "Registro eliminado");
        return "redirect:/encargado";
    }
}