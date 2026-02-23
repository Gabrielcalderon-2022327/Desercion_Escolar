package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Economia")
public class Economia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_economia")
    private Integer idEconomia;

    @Column(name = "ingresos_economia")
    private Double ingresosEconomia;

    @Column(name = "fecha_economia")
    private LocalDate fechaEconomia;

    @Column(name = "id_estudiante")
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
