package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table(name = "Estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer id_estudiante;

    @Column(name = "nombre_estudiante")
    @NotBlank(message = "El nombre del estudiante es un campo obligatorio")
    private String nombre_estudiante;

    @Column(name = "apellido_estudiante")
    @NotBlank(message = "El apellido es un campo obligatorio")
    private String apellido_estudiante;

    @Column(name = "fecha_nacimiento_estudiante")
    @NotNull(message = "La fecha del nacimiento no puede ser nula")
    @Past(message = "Error: la fecha no puede ser futura")
    private LocalDate fecha_nacimiento_estudiante;

    @Column(name = "direccion_estudiante")
    @NotBlank(message = "La direccion del estudiante es un campo obligatorio")
    private String direccion_estudiante;

    @Column(name = "telefono_estudiante")
    @NotNull(message = "El telefono del estudiante es un campo obligatorio")
    private Integer telefono_estudiante;

    @Column(name = "fk_id_encargado")
    @NotNull(message = "El id del encargado es obligatorio")
    private Integer fk_id_encargado;

    @ManyToOne
    @JoinColumn(name = "fk_id_encargado", insertable = false, updatable = false)
    private Encargado encargado;

    @Column(name = "fk_id_grado")
    @NotNull(message = "El id del grado es obligatorio")
    private Integer fk_id_grado;

    @ManyToOne
    @JoinColumn(name = "fk_id_grado", insertable = false, updatable = false)
    private Grado grado;

    public Integer getId_estudiante() {return id_estudiante;}

    public void setId_estudiante(Integer id_estudiante) {this.id_estudiante = id_estudiante;}

    public String getNombre_estudiante() {return nombre_estudiante;}

    public void setNombre_estudiante(String nombre_estudiante) {this.nombre_estudiante = nombre_estudiante;}

    public String getApellido_estudiante() {return apellido_estudiante;}

    public void setApellido_estudiante(String apellido_estudiante) {this.apellido_estudiante = apellido_estudiante;}

    public LocalDate getFecha_nacimiento_estudiante() {return fecha_nacimiento_estudiante;}

    public void setFecha_nacimiento_estudiante(LocalDate fecha_nacimiento_estudiante) {this.fecha_nacimiento_estudiante = fecha_nacimiento_estudiante;}

    public String getDireccion_estudiante() {return direccion_estudiante;}

    public void setDireccion_estudiante(String direccion_estudiante) {this.direccion_estudiante = direccion_estudiante;}

    public Integer getTelefono_estudiante() {return telefono_estudiante;}

    public void setTelefono_estudiante(Integer telefono_estudiante) {this.telefono_estudiante = telefono_estudiante;}

    public Integer getFk_id_encargado() {return fk_id_encargado;}

    public void setFk_id_encargado(Integer fk_id_encargado) {this.fk_id_encargado = fk_id_encargado;}

    public Integer getFk_id_grado() {return fk_id_grado;}

    public void setFk_id_grado(Integer fk_id_grado) {this.fk_id_grado = fk_id_grado;}

    public Encargado getEncargado() {return encargado;}

    public void setEncargado(Encargado encargado) {this.encargado = encargado;}

    public Grado getGrado() {return grado;}

    public void setGrado(Grado grado) {this.grado = grado;}
}