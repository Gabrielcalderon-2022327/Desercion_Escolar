package com.scrum.ProyectoDesercion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer idAlerta;

    @Column(name = "fecha_alerta")
    @NotNull(message = "La fecha no puede ser nula")
    @Past(message = "Error: la fecha no puede ser futura")
    @JsonProperty("fecha_alerta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaAlerta;

    @Column(name = "tipo_alerta")
    @NotBlank(message = "El tipo de alerta no puede estar vacío")
    @JsonProperty("tipo_alerta")
    private String tipoAlerta;

    @Column(name = "incidente_alerta")
    @NotBlank(message = "El incidente de alerta no puede estar vacío")
    @JsonProperty("incidente_alerta")
    private String incidenteAlerta;

    @Column(name = "fk_id_riesgo")
    @NotNull(message = "El ID de riesgo es obligatorio")
    @JsonProperty("fk_id_riesgo")
    private Integer fkIdRiesgo;

    @Column(name = "id_empleado")
    @NotNull(message = "El ID del empleado es obligatorio")
    @JsonProperty("id_empleado")
    private Integer idEmpleado;

    public Alerta() {}

    // --- Getters y Setters ---
    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }

    public LocalDate getFechaAlerta() { return fechaAlerta; }
    public void setFechaAlerta(LocalDate fechaAlerta) { this.fechaAlerta = fechaAlerta; }

    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }

    public String getIncidenteAlerta() { return incidenteAlerta; }
    public void setIncidenteAlerta(String incidenteAlerta) { this.incidenteAlerta = incidenteAlerta; }

    public Integer getFkIdRiesgo() { return fkIdRiesgo; }
    public void setFkIdRiesgo(Integer fkIdRiesgo) { this.fkIdRiesgo = fkIdRiesgo; }

    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
}