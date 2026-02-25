package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table (name = "Alerta")

public class Alerta {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)


@Column(name = "id_empleado")
private Integer idAlerta;

@Column(name = "fecha_alerta")
@NotNull
@Past(message = "error la fecha no puede ser futura")
private LocalDate fechaAlerta;

@Column(name = "tipo_alerta")
@NotBlank (message = " el tipo de alerta no puede estar vacio")
private String tipoAlerta;

@Column(name = "incidente_alerta")
@NotBlank(message = "el incidente de alerta no puede estar vacio")
private String incidenteAlerta;


// getter y setters


    public Integer getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
    }

    public @NotNull @Past(message = "error la fecha no puede ser futura") LocalDate getFecha_alerta() {
        return fechaAlerta;
    }

    public void setFecha_alerta(@NotNull @Past LocalDate fecha_alerta) {
        this.fechaAlerta = fecha_alerta;
    }

    public String getTipo_alerta() {
        return tipoAlerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipoAlerta = tipo_alerta;
    }

    public String getIncidente_alerta() {
        return incidenteAlerta;
    }

    public void setIncidente_alerta(String incidente_alerta) {
        this.incidenteAlerta = incidente_alerta;
    }
}
