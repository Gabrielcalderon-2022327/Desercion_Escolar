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

}
