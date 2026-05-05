package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table(name = "Asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer id_asistencia;

    @Column(name = "fecha_asistencia")
    @NotNull(message = "La fecha de asistencia es un campo  obligatorio")
    @Past(message = "La fecha no puede ser futura")
    private LocalDate fecha_asistencia;

    @Column(name = "estado_asistencia")
    @NotBlank(message = "El estado de asistencia es un campo obligatorio")
    private String estado_asistencia;

    @Column(name = "fk_id_estudiante")
    @NotNull(message = "El id del estudiante es un campo  obligatorio")
    private Integer fk_id_estudiante;

    @ManyToOne
    @JoinColumn(name = "fk_id_estudiante", insertable = false, updatable = false)
    private Estudiante estudiante;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Integer getId_asistencia() {
        return id_asistencia;
    }
    public void setId_asistencia(Integer id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public LocalDate getFecha_asistencia() {
        return fecha_asistencia;
    }
    public void setFecha_asistencia(LocalDate fecha_asistencia) {
        this.fecha_asistencia = fecha_asistencia;
    }

    public String getEstado_asistencia() {
        return estado_asistencia;
    }
    public void setEstado_asistencia(String estado_asistencia) {
        this.estado_asistencia = estado_asistencia;
    }

    public Integer getFk_id_estudiante() {
        return fk_id_estudiante;
    }
    public void setFk_id_estudiante(Integer fk_id_estudiante) {
        this.fk_id_estudiante = fk_id_estudiante;
    }
}
