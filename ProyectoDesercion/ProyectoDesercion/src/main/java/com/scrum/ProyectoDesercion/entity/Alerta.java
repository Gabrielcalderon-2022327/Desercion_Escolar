package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado")
    private Integer idAlerta;

    @Column(name = "fecha_alerta")
    @NotNull(message = "La fecha no puede ser nula")
    @Past(message = "Error: la fecha no puede ser futura")
    private LocalDate fechaAlerta;

    @Column(name = "tipo_alerta")
    @NotBlank(message = "El tipo de alerta no puede estar vacío")
    private String tipoAlerta;

    @Column(name = "incidente_alerta")
    @NotBlank(message = "El incidente de alerta no puede estar vacío")
    private String incidenteAlerta;

    // Getters y Setters
    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }

    public LocalDate getFechaAlerta() { return fechaAlerta; }
    public void setFechaAlerta(LocalDate fechaAlerta) { this.fechaAlerta = fechaAlerta; }

    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }

    public String getIncidenteAlerta() { return incidenteAlerta; }
    public void setIncidenteAlerta(String incidenteAlerta) { this.incidenteAlerta = incidenteAlerta; }
}