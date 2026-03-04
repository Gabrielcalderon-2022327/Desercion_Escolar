package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table(name = "Economia")
public class Economia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_economia")
    private Integer idEconomia;

    @NotNull(message = "Los ingresos es un campo obligatorio.")
    @Column(name = "ingresos_economia")
    private Double ingresosEconomia;

    @NotNull(message = "La fecha de la economia es un campo obligatorio.")
    @Past(message = "La fecha no puede ser futura.")
    @Column(name = "fecha_economia")
    private LocalDate fechaEconomia;

    @NotNull(message = "El id del estudiante es un campo obligatorio.")
    @Column(name = "fk_id_estudiante")
    private Integer idEstudiante;

    //getter y setter


    public Integer getIdEconomia() {
        return idEconomia;
    }

    public void setIdEconomia(Integer idEconomia) {
        this.idEconomia = idEconomia;
    }

    public Double getIngresosEconomia() {
        return ingresosEconomia;
    }

    public void setIngresosEconomia(Double ingresosEconomia) {
        this.ingresosEconomia = ingresosEconomia;
    }

    public LocalDate getFechaEconomia() {
        return fechaEconomia;
    }

    public void setFechaEconomia(LocalDate fechaEconomia) {
        this.fechaEconomia = fechaEconomia;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
}
