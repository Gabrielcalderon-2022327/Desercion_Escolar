package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "MateriasF")
public class MateriasF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materiasf")
    private Integer idMateriasF;

    @Column(name = "nombre_materiaf")
    @NotBlank(message = "El nombre de materia es un campo obligatorio")
    private String nombreMateriaF;

    @Column(name = "descripcion_materiaf")
    @NotBlank(message = "La descripcion es un campo obligatorio")
    private String descripcionMateriaF;

    @Column(name = "fecha_alerta_materiaf")
    @NotNull(message = "La fecha no puede ser nula")
    @PastOrPresent(message = "Error: la fecha no puede ser futura")
    private LocalDate fechaAlertaMateriaF;

    @Column(name = "fk_id_maestro")
    @NotNull(message = "El id del maestro es obligatorio")
    private Integer idMaestro;

    @ManyToOne
    @JoinColumn(name = "fk_id_maestro", insertable = false, updatable = false)
    private Maestro maestro;

    @Column(name = "fk_id_estudiante")
    @NotNull(message = "El id del estudiante es obligatorio")
    private Integer idEstudiante;

    @ManyToOne
    @JoinColumn(name = "fk_id_estudiante", insertable = false, updatable = false)
    private Estudiante estudiante;

    public Integer getIdMateriasF() {return idMateriasF;}

    public void setIdMateriasF(Integer idMateriasF) {this.idMateriasF = idMateriasF;}

    public String getNombreMateriaF() {return nombreMateriaF;}

    public void setNombreMateriaF(String nombreMateriaF) {this.nombreMateriaF = nombreMateriaF;}

    public String getDescripcionMateriaF() {return descripcionMateriaF;}

    public void setDescripcionMateriaF(String descripcionMateriaF) {this.descripcionMateriaF = descripcionMateriaF;}

    public LocalDate getFechaAlertaMateriaF() {return fechaAlertaMateriaF;}

    public void setFechaAlertaMateriaF(LocalDate fechaAlertaMateriaF) {this.fechaAlertaMateriaF = fechaAlertaMateriaF;}

    public Integer getIdMaestro() {return idMaestro;}

    public void setIdMaestro(Integer idMaestro) {this.idMaestro = idMaestro;}

    public Integer getIdEstudiante() {return idEstudiante;}

    public void setIdEstudiante(Integer idEstudiante) {this.idEstudiante = idEstudiante;}

    public Maestro getMaestro() {return maestro;}

    public void setMaestro(Maestro maestro) {this.maestro = maestro;}

    public Estudiante getEstudiante() {return estudiante;}

    public void setEstudiante(Estudiante estudiante) {this.estudiante = estudiante;}
}