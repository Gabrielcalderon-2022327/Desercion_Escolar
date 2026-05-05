package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer id_alerta;

    @Column(name = "fecha_alerta")
    @NotNull(message = "La fecha no puede ser nula")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha_alerta;

    @Column(name = "tipo_alerta")
    @NotBlank(message = "El tipo de alerta no puede estar vacío")
    private String tipo_alerta;

    @Column(name = "incidente_alerta")
    @NotBlank(message = "El incidente de alerta no puede estar vacío")
    private String incidente_alerta;

    @Column(name = "fk_id_riesgo")
    @NotNull(message = "El ID de riesgo es obligatorio")
    private Integer fk_id_riesgo;

    @ManyToOne
    @JoinColumn(name = "fk_id_riesgo", insertable = false, updatable = false)
    private Riesgo riesgo;

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    public Integer getId_alerta() {
        return id_alerta;
    }

    public void setId_alerta(Integer id_alerta) {
        this.id_alerta = id_alerta;
    }

    public LocalDate getFecha_alerta() {
        return fecha_alerta;
    }

    public void setFecha_alerta(LocalDate fecha_alerta) {
        this.fecha_alerta = fecha_alerta;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public String getIncidente_alerta() {
        return incidente_alerta;
    }

    public void setIncidente_alerta(String incidente_alerta) {
        this.incidente_alerta = incidente_alerta;
    }

    public Integer getFk_id_riesgo() {
        return fk_id_riesgo;
    }

    public void setFk_id_riesgo(Integer fk_id_riesgo) {
        this.fk_id_riesgo = fk_id_riesgo;
    }
}