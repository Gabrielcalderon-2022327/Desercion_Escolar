package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MateriasF")
public class MateriasF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materiasf")
    private Integer idMateriasF;

    @Column(name = "nombre_materiaf")
    private String nombreMateriaF;

    @Column(name = "descripcion_materiaf")
    private String descripcionMateriaF;

    @Column(name = "fecha_alerta_materiaf")
    private LocalDate fechaAlertaMateriaF;

    @Column(name = "fk_id_maestro")
    private Integer idMaestro;

    @Column(name = "fk_id_estudiante")
    private Integer idEstudiante;

    //Generar getter and setter a todos

    public Integer getIdMateriasF() {
        return idMateriasF;
    }

    public void setIdMateriasF(Integer idMateriasF) {
        this.idMateriasF = idMateriasF;
    }

    public String getNombreMateriaF() {
        return nombreMateriaF;
    }

    public void setNombreMateriaF(String nombreMateriaF) {
        this.nombreMateriaF = nombreMateriaF;
    }

    public String getDescripcionMateriaF() {
        return descripcionMateriaF;
    }

    public void setDescripcionMateriaF(String descripcionMateriaF) {
        this.descripcionMateriaF = descripcionMateriaF;
    }

    public LocalDate getFechaAlertaMateriaF() {
        return fechaAlertaMateriaF;
    }

    public void setFechaAlertaMateriaF(LocalDate fechaAlertaMateriaF) {
        this.fechaAlertaMateriaF = fechaAlertaMateriaF;
    }

    public Integer getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
}
