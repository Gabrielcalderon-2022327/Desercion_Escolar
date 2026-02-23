package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "MateriasF")
public class MateriasF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materiasf")
    private Integer idMateriasf;

    @Column(name = "nombre_materiaf")
    private String nombreMateriaf;

    @Column(name = "descripcion_materiaf")
    private String apellidoEmpleado;

    @Column(name = "fecha_alerta_materiaf")
    private LocalDate fechaAlertaMateriaf;

    @Column(name = "id_maestro")
    private Integer idMaestro;

    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    public Integer getIdMateriasf() {
        return idMateriasf;
    }

    public void setIdMateriasf(Integer idMateriasf) {
        this.idMateriasf = idMateriasf;
    }

    public String getNombreMateriaf() {
        return nombreMateriaf;
    }

    public void setNombreMateriaf(String nombreMateriaf) {
        this.nombreMateriaf = nombreMateriaf;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public LocalDate getFechaAlertaMateriaf() {
        return fechaAlertaMateriaf;
    }

    public void setFechaAlertaMateriaf(LocalDate fechaAlertaMateriaf) {
        this.fechaAlertaMateriaf = fechaAlertaMateriaf;
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
