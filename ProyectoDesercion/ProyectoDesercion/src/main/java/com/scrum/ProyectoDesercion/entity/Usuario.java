package com.scrum.ProyectoDesercion.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name ="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "correo_usuario")
    private String correoUsuario;

    @Column(name = "contra_usuario")
    private String contraUsuario;

    @Column(name = "rol_usuario")
    private String rolUsuario;

    @Column(name = "creacion_usuario")
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

    public void setCreacionUsuario(Date creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }
}
