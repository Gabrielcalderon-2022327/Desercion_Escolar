package com.scrum.ProyectoDesercion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/paginaprincipal"})
    public String dashboard() {
        return "Pagina principal";
    }

    @GetMapping("/estudiante")
    public String estudiantes() {
        return "Estudiantes";
    }

    @GetMapping("/encargados")
    public String encargados() {
        return "Encargado";
    }

    @GetMapping("/grados")
    public String grados() {
        return "Grado";
    }

    @GetMapping("/maestros")
    public String maestros() {
        return "Maestro";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "Usuario";
    }

    @GetMapping("/asistencias")
    public String asistencias() {
        return "Asistencia";
    }

    @GetMapping("/materias-f")
    public String materiasF() {
        return "MateriasF";
    }

    @GetMapping("/economia")
    public String economia() {
        return "Economia";
    }

    @GetMapping("/riesgo")
    public String riesgo() {
        return "Riesgo";
    }

    @GetMapping("/alertas")
    public String alertas() {
        return "Alerta";
    }
}