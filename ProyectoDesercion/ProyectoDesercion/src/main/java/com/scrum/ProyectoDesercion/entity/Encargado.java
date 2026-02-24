package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Encargado")
public class Encargado {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encargado")
    private Integer id_encargado;

    @Column(name = "nombre_encargado")
    @NotBlank(message = "El nombre es un campo obligatorio")
    private String nombre_encargado;

    @Column(name = "apellido_encargado")
    @NotBlank(message = "El apellido es un campo obligatorio")
    private String apellido_encargado;

    @Column(name = "fecha_nacimiento_encargado")
    private LocalDate fecha_nacimiento_encargado;

    @Column(name = "direccion_encargado")
    @NotBlank(message = "La direccion es un campo obligatorio")
    private String direccion_encargado;

    @Column(name = "telefono_encargado")
    @NotNull(message = "El telefono es un campo obligatorio")
    private int telefono_encargado;


    public Integer getId_encargado() {
        return id_encargado;
    }

    public void setId_encargado(Integer id_encargado) {
        this.id_encargado = id_encargado;
    }

    public String getNombre_encargado() {
        return nombre_encargado;
    }

    public void setNombre_encargado(String nombre_encargado) {
        this.nombre_encargado = nombre_encargado;
    }

    public String getApellido_encargado() {
        return apellido_encargado;
    }

    public void setApellido_encargado(String apellido_encargado) {
        this.apellido_encargado = apellido_encargado;
    }

    public LocalDate getFecha_nacimiento_encargado() {
        return fecha_nacimiento_encargado;
    }

    public void setFecha_nacimiento_encargado(LocalDate fecha_nacimiento_encargado) {
        this.fecha_nacimiento_encargado = fecha_nacimiento_encargado;
    }

    public String getDireccion_encargado() {
        return direccion_encargado;
    }

    public void setDireccion_encargado(String direccion_encargado) {
        this.direccion_encargado = direccion_encargado;
    }

    public int getTelefono_encargado() {
        return telefono_encargado;
    }

    public void setTelefono_encargado(int telefono_encargado) {
        this.telefono_encargado = telefono_encargado;
    }
}
