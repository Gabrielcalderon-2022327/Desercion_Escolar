package com.scrum.ProyectoDesercion.controller;

import com.scrum.ProyectoDesercion.entity.Usuario;
import com.scrum.ProyectoDesercion.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public String visita(HttpSession session){
        if(session.getAttribute("username")!= null){
            return "redirect:/index";
        } else{
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String cargarLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session){
        Usuario user = service.login(email, password);
        if(user != null){
            session.setAttribute("username", email);
            return "redirect:/index";
        } else{
            session.setAttribute("username", null);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("username", null);
        return "redirect:/login";
    }
}
