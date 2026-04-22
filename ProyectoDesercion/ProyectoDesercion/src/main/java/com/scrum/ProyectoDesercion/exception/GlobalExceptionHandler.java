package com.scrum.ProyectoDesercion.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validarAnotacionesEntidad(MethodArgumentNotValidException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String mensajes = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.joining(" | "));
        redirectAttributes.addFlashAttribute("error", mensajes);
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String validarConstraintViolation(ConstraintViolationException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String mensaje = ex.getConstraintViolations().stream()
                .map(err -> err.getMessage())
                .collect(Collectors.joining(" | "));
        redirectAttributes.addFlashAttribute("error", mensaje);
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String validarErroresConstraint(SQLIntegrityConstraintViolationException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "Error en base de datos: Violación de llave foránea o dato duplicado.");
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String validarJSON(HttpMediaTypeNotSupportedException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "El archivo o formato enviado no es válido.");
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String validarTipoDato(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "Tipo de dato incorrecto o mala estructura en el formulario.");
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String validacionesNegocio(IllegalArgumentException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String captarThrows(ResourceNotFoundException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:" + getReferer(request);
    }

    // Método auxiliar para devolver al usuario a la vista donde ocurrió el error
    private String getReferer(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer == null || referer.isEmpty()) {
            referer = request.getContextPath() + "/";
        }
        return referer;
    }
}