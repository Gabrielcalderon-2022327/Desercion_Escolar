package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table (name = "Maestro")
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maestro")
    private Integer idMaestro;

    @NotNull(message = "El nombre del maestro es obligatorio.")
    @Column(name = "nombre_maestro")
    private String nombreMaestro;

    @NotNull(message = "¿Cual es la especialidad del maestro?")
    @Column(name = "especialidad_maestro")
    private String especialidadMaestro;

    @NotNull(message = "El numero de telefo es obligatorio.")
    @Column(name = "telefono_maestro")
    private Integer telefonoMaestro;

    @NotNull(message = "¿Cuál es el id de usuario?")
    @Column(name = "fk_id_usuario")
    private Integer idUsuario;

    public Integer getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public String getEspecialidadMaestro() {
        return especialidadMaestro;
    }

    public void setEspecialidadMaestro(String especialidadMaestro) {
        this.especialidadMaestro = especialidadMaestro;
    }

    public Integer getTelefonoMaestro() {
        return telefonoMaestro;
    }

    public void setTelefonoMaestro(Integer telefonoMaestro) {
        this.telefonoMaestro = telefonoMaestro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
