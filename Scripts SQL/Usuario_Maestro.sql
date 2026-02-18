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

delimiter $$
create procedure sp_buscar_usuario(in p_id int)
begin
	select * from Usuario where id_usuario = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_agregar_maestro(
    in p_nombre varchar(60),
    in p_especialidad varchar(45),
    in p_telefono int,
    in p_id_usuario int
)
begin
    insert into Maestro(nombre_maestro, especialidad_maestro, telefono_maestro, fk_id_usuario)
    values (p_nombre, p_especialidad, p_telefono, p_id_usuario);
end $$
delimiter ;
 
delimiter $$
create procedure sp_editar_maestro(
    in p_id int,
    in p_nombre varchar(60),
    in p_especialidad varchar(45),
    in p_telefono int
)
begin
    update Maestro
    set nombre_maestro = p_nombre,
        especialidad_maestro = p_especialidad,
        telefono_maestro = p_telefono
    where id_maestro = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_maestro(in p_id int)
begin
    delete from Maestro where id_maestro = p_id;
end $$
delimiter ;
 
delimiter $$
create procedure sp_listar_maestro()
begin
    select * from Maestro;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_maestro(in p_id int)
begin
	select * from Maestro where id_maestro = p_id;
end $$
delimiter ;
 

-- Datos
call sp_agregar_usuario('gabrielCalderon@gmail.com', '12345', 'Administrador', '2024-01-01');
call sp_agregar_usuario('joelArchila@gmail.com', 'abcde', 'Administrador', '2024-01-02');
call sp_agregar_usuario('efrainCallejas@gmail.com', 'fghi', 'Administrador', '2024-01-03');
call sp_agregar_usuario('orientador1@gmail.com', 'jklmn', 'Orientador', '2024-01-04');
call sp_agregar_usuario('orientador2@gmail.com', 'opqrs', 'Orientador', '2024-01-05');
call sp_agregar_usuario('orientador3@gmail.com', 'tuvw', 'Orientador', '2024-01-06');
call sp_agregar_usuario('maestro1@gmail.com', '67890', 'Maestro', '2024-01-07');
call sp_agregar_usuario('maestro2@gmail.com', '54321', 'Maestro', '2024-01-08');
call sp_agregar_usuario('maestro3@gmail.com', '09876', 'Maestro', '2024-01-09');
call sp_agregar_usuario('maestro4@gmail.com', '4627', 'Maestro', '2024-01-10');

call sp_agregar_maestro('Carlos Martínez', 'Matemáticas', 551234567, 7);
call sp_agregar_maestro('Laura Gómez', 'Lenguaje', 552345678, 8);
call sp_agregar_maestro('Miguel Hernández', 'Ciencias Naturales', 553456789, 9);
call sp_agregar_maestro('Ana Rodríguez', 'Historia', 554567890, 10);
call sp_agregar_maestro('José Ramírez', 'Física', 555678901, 7);
call sp_agregar_maestro('Patricia López', 'Química', 556789012, 8);
call sp_agregar_maestro('Ricardo Torres', 'Inglés', 557890123, 9);
call sp_agregar_maestro('Daniela Castro', 'Informática', 558901234, 10);
call sp_agregar_maestro('Fernando Ruiz', 'Educación Física', 559012345, 7);
call sp_agregar_maestro('María Sánchez', 'Geografía', 550123456, 8);




