package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name ="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "correo_usuario")
    @NotBlank(message = "El correo es un campo obligatorio")
    private String correoUsuario;

    @Column(name = "contra_usuario")
    @NotBlank(message = "La contraseña es un campo obligatorio")
    private String contraUsuario;

    @Column(name = "rol_usuario")
    @NotBlank(message = "El rol de usuario es un campo obligatorio")
    private String rolUsuario;

    @Column(name = "creacion_usuario")
    @NotNull(message = "La fecha de creacion es un campo obligatorio")
    @Past(message = "La fecha no puede ser futura")
    private Date creacionUsuario;

    //Generar getter and setter a todos


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        this.contraUsuario = contraUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Date getCreacionUsuario() {
        return creacionUsuario;
    }

    public void setCreacionUsuario(LocalDate creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }

}
