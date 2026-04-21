package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.service.UsuarioService;
import com.scrum.ProyectoDesercion.validator.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/usuarios")
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
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/buscar")
    public String buscarUsuario(RedirectAttributes redirectAttributes, @RequestParam Integer idUsuario){
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        redirectAttributes.addFlashAttribute("usuarios", List.of(usuario));
        return ("redirect:/usuarios");
    }

    @PostMapping("/usuarios/crear")
    public String crearUsuario(@Valid @RequestParam String correoUsuario,
                               @Valid @RequestParam String contraUsuario,
                               @Valid @RequestParam String rolUsuario){
        Usuario newUsuario = new Usuario();
        newUsuario.setCorreoUsuario(correoUsuario);
        newUsuario.setContraUsuario(contraUsuario);
        newUsuario.setRolUsuario(rolUsuario);
        newUsuario.setCreacionUsuario(LocalDate.now());
        validator.validarRegistro(newUsuario);
        usuarioService.saveUsuario(newUsuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/editar")
    public String editarUsuario(@Valid @RequestParam Integer idUsuario,
                                @Valid @RequestParam String correoUsuario,
                                @Valid @RequestParam String contraUsuario,
                                @Valid @RequestParam String rolUsuario,
                                @Valid @RequestParam LocalDate creacionUsuario){
        Usuario newUsuario = new Usuario();
        newUsuario.setCorreoUsuario(correoUsuario);
        newUsuario.setContraUsuario(contraUsuario);
        newUsuario.setRolUsuario(rolUsuario);
        newUsuario.setCreacionUsuario(creacionUsuario);
        usuarioService.updateUsuario(idUsuario, newUsuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id){
        usuarioService.deleteUsuario(id);
        return "redirect:/usuarios";
    }
}


