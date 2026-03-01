package com.scrum.ProyectoDesercion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Riesgo")
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_riesgo")
    private Integer idRiesgo;

    @Column(name = "nivel_riesgo")
    @NotBlank(message = "El nivel de riesgo no puede estar vacío")
    @JsonProperty("nivel_riesgo")
    private String nivelRiesgo;

    @Column(name = "descripcion_riesgo")
    @NotBlank(message = "La descripción del riesgo no puede estar vacía")
    @JsonProperty("descripcion_riesgo")
    private String descripcionRiesgo;

    // --- LA REGLA DEL PRIMO: unique = true ---
    @Column(name = "fk_id_estudiante", unique = true)
    @NotNull(message = "El ID del estudiante es obligatorio")
    @JsonProperty("fk_id_estudiante")
    private Integer fkIdEstudiante;

    public Riesgo() {}

    // --- Getters y Setters ---
    public Integer getIdRiesgo() { return idRiesgo; }
    public void setIdRiesgo(Integer idRiesgo) { this.idRiesgo = idRiesgo; }

    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public String getDescripcionRiesgo() { return descripcionRiesgo; }
    public void setDescripcionRiesgo(String descripcionRiesgo) { this.descripcionRiesgo = descripcionRiesgo; }

    public Integer getFkIdEstudiante() { return fkIdEstudiante; }
    public void setFkIdEstudiante(Integer fkIdEstudiante) { this.fkIdEstudiante = fkIdEstudiante; }
}