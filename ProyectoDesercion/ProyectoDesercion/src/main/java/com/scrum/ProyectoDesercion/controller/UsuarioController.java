package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.service.UsuarioService;
import com.scrum.ProyectoDesercion.validator.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioValidator validator;

    public UsuarioController(UsuarioService usuarioService, UsuarioValidator validator) {
        this.usuarioService = usuarioService;
        this.validator = validator;
    }

    @GetMapping("/usuarios")
    public String cargarUsuarios(Model model){
        if(!model.containsAttribute("usuarios")){
            model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        }
        return "Usuario";
    }

    @GetMapping("/usuarios/listar")
    public String listarUsuarios(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("usuarios", usuarioService.getAllUsuarios());
        redirectAttributes.addFlashAttribute("success", "Se actualizó la tabla correctamente!");
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/buscar")
    public String buscarUsuario(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario){
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        redirectAttributes.addFlashAttribute("usuarios", List.of(usuario));
        redirectAttributes.addFlashAttribute("success", "Se encontró el registro!");
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/crear")
    public String crearUsuario( RedirectAttributes redirectAttributes,
                                @Valid @RequestParam String correoUsuario,
                                @Valid @RequestParam String contraUsuario,
                                @Valid @RequestParam String rolUsuario){
        Usuario newUsuario = new Usuario();
        newUsuario.setCorreoUsuario(correoUsuario);
        newUsuario.setContraUsuario(contraUsuario);
        newUsuario.setRolUsuario(rolUsuario);
        newUsuario.setCreacionUsuario(LocalDate.now());
        validator.validarRegistro(newUsuario);
        usuarioService.saveUsuario(newUsuario);
        redirectAttributes.addFlashAttribute("success", "Se creo un nuevo registro!");
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/editar")
    public String editarUsuario(RedirectAttributes redirectAttributes,
                                @Valid @RequestParam Integer idUsuario,
                                @Valid @RequestParam String correoUsuario,
                                @Valid @RequestParam String contraUsuario,
                                @Valid @RequestParam String rolUsuario,
                                @Valid @RequestParam LocalDate creacionUsuario){
        Usuario newUsuario = new Usuario();
        newUsuario.setCorreoUsuario(correoUsuario);
        newUsuario.setContraUsuario(contraUsuario);
        newUsuario.setRolUsuario(rolUsuario);
        newUsuario.setCreacionUsuario(creacionUsuario);

        validator.validarUpdate(newUsuario, idUsuario);
        usuarioService.updateUsuario(idUsuario,newUsuario);

        redirectAttributes.addFlashAttribute("success", "Se actualizó el registro no: " + idUsuario + "!");
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(RedirectAttributes redirectAttributes, @PathVariable Integer id){
        usuarioService.deleteUsuario(id);
        redirectAttributes.addFlashAttribute("success", "Se eliminó el registro correctamente!");
        return "redirect:/usuarios";
    }
}