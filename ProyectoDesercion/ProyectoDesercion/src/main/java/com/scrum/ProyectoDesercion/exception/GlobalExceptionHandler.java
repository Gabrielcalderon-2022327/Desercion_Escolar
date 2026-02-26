package com.scrum.ProyectoDesercion.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validarAnotacionesEntidad(MethodArgumentNotValidException ex) {
        List<String> mensajes = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(Map.of("Error", mensajes));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> validarErroresConstraint(SQLIntegrityConstraintViolationException ex){
        return ResponseEntity.badRequest().body(Map.of("Error", "No existe esa llave foranea"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> validarJSON(HttpMediaTypeNotSupportedException ex){
        return ResponseEntity.badRequest().body(Map.of("Error", "El archivo no es tipo JSON"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> validarTipoDato(){
        return ResponseEntity.badRequest().body(Map.of("Error", "El tipo de dato esta incorrecto o hay mala estructura en el JSON"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> captarThrows(ResourceNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> validacionesNegocio(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(Map.of("Error", ex.getMessage()));
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 3c84bceabbce306173aad04f1d9cd4a60fa59f31
