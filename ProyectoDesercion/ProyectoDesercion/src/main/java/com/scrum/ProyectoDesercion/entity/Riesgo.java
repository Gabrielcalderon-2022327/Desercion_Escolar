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
    private Integer id_riesgo;

    @Column(name = "nivel_riesgo")
    @NotBlank(message = "El nivel de riesgo no puede estar vacío")
    private String nivel_riesgo;

    @Column(name = "descripcion_riesgo")
    @NotBlank(message = "La descripción del riesgo no puede estar vacía")
    private String descripcion_riesgo;

    @Column(name = "fk_id_estudiante", unique = true)
    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer fk_id_estudiante;


    public Integer getId_riesgo() {
        return id_riesgo;
    }

    public void setId_riesgo(Integer id_riesgo) {
        this.id_riesgo = id_riesgo;
    }

    public String getNivel_riesgo() {
        return nivel_riesgo;
    }

    public void setNivel_riesgo(String nivel_riesgo) {
        this.nivel_riesgo = nivel_riesgo;
    }

    public Integer getFk_id_estudiante() {
        return fk_id_estudiante;
    }

    public void setFk_id_estudiante(Integer fk_id_estudiante) {
        this.fk_id_estudiante = fk_id_estudiante;
    }

    public String getDescripcion_riesgo() {
        return descripcion_riesgo;
    }

    public void setDescripcion_riesgo(String descripcion_riesgo) {
        this.descripcion_riesgo = descripcion_riesgo;
    }
}