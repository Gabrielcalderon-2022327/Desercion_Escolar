package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Grado")
public class Grado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado")
    private Integer id_grado;

    @Column(name = "nombre_grado")
    @NotBlank(message = "El campo de grado esta vacio, es obligatorio")
    private String nombre_grado;

    @Column(name = "fk_id_maestro")
    @NotNull(message = "El ID del maestro es obligatorio")
    private Integer fk_id_maestro;

    public Integer getId_grado() {
        return id_grado;
    }

    public void setId_grado(Integer id_grado) {
        this.id_grado = id_grado;
    }

    public String getNombre_grado() {
        return nombre_grado;
    }

    public void setNombre_grado(String nombre_grado) {
        this.nombre_grado = nombre_grado;
    }

    public Integer getFk_id_maestro() {
        return fk_id_maestro;
    }

    public void setFk_id_maestro(Integer fk_id_maestro) {
        this.fk_id_maestro = fk_id_maestro;
    }
}
