drop database if exists DB_DesercionEscolar_in5cm;
create database DB_DesercionEscolar_in5cm;
use DB_DesercionEscolar_in5cm;

create table Usuario (
    id_usuario int primary key auto_increment,
    correo_usuario varchar(60) not null,
    contra_usuario varchar(30) not null,
    rol_usuario varchar(20) not null,
    creacion_usuario date not null
);
 
 
create table Maestro (
    id_maestro int primary key auto_increment,
    nombre_maestro varchar (60) not null,
    especialidad_maestro varchar (45) not null,
    telefono_maestro int not null,
    fk_id_usuario int not null,
    constraint foreign key (fk_id_usuario) references Usuario(id_usuario)
);

-- PROCEDIMIENTOS
delimiter $$
create procedure sp_agregar_usuario(
    in p_correo varchar(60),
    in p_contra varchar(30),
    in p_rol varchar(20),
    in p_fecha date
)
begin
    insert into Usuario(correo_usuario, contra_usuario, rol_usuario, creacion_usuario)
    values (p_correo, p_contra, p_rol, p_fecha);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_usuario(
    in p_id int,
    in p_correo varchar(60),
    in p_contra varchar(30),
    in p_rol varchar(20)
)
begin
    update Usuario
    set correo_usuario = p_correo,
        contra_usuario = p_contra,
        rol_usuario = p_rol
    where id_usuario = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_usuario(in p_id int)
begin
    delete from Usuario
    where id_usuario = p_id;
end $$
delimiter ;
 
delimiter $$
create procedure sp_listar_usuario()
begin
    select * from Usuario;
end $$
delimiter ;
