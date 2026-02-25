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

create table Grado (
    id_grado int primary key auto_increment,
    nombre_grado varchar (45) not null,
    fk_id_maestro int not null,
    constraint foreign key (fk_id_maestro) references Maestro(id_maestro)
);

 
create table Encargado (
    id_encargado int primary key auto_increment,
    nombre_encargado varchar (60) not null,
    apellido_encargado varchar (50) not null,
    fecha_nacimiento_encargado date not null,
    direccion_encargado varchar (60) not null,
    telefono_encargado int not null
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

-- GRADO --
 delimiter $$
create procedure sp_agregar_grado(
    in p_nombre varchar(45),
    in p_id_maestro int
)
begin
    insert into grado(nombre_grado, fk_id_maestro)
    values (p_nombre, p_id_maestro);
end $$
 
  delimiter $$
create procedure sp_editar_grado(
    in p_id int,
    in p_nombre varchar(45),
    in p_id_maestro int
)
begin
    update grado
    set nombre_grado = p_nombre,
        fk_id_maestro = p_id_maestro
    where id_grado = p_id;
end $$
 
  delimiter $$
create procedure sp_eliminar_grado(in p_id int)
begin
    delete from grado
    where id_grado = p_id;
end $$

delimiter $$
create procedure sp_listar_grado()
begin
    select g.id_grado,
           g.nombre_grado,
           m.nombre_maestro
    from grado g
    inner join maestro m
        on g.fk_id_maestro = m.id_maestro;
end $$
delimiter ;


delimiter $$
create procedure sp_buscar_grado_por_id(
    in p_id int
)
begin
    select g.id_grado,
           g.nombre_grado,
           m.nombre_maestro
    from grado g
    inner join maestro m
        on g.fk_id_maestro = m.id_maestro
    where g.id_grado = p_id;
end $$
delimiter ;


 
-- ENCARGADO
  delimiter $$
create procedure sp_agregar_encargado(
    in p_nombre varchar(60),
    in p_apellido varchar(50),
    in p_fecha date,
    in p_direccion varchar(60),
    in p_telefono int
)
begin
    insert into encargado(nombre_encargado, apellido_encargado,
        fecha_nacimiento_encargado, direccion_encargado, telefono_encargado)
    values (p_nombre, p_apellido, p_fecha, p_direccion, p_telefono);
end $$

delimiter $$
create procedure sp_editar_encargado(
    in p_id int,
    in p_nombre varchar(60),
    in p_apellido varchar(50),
    in p_fecha date,
    in p_direccion varchar(60),
    in p_telefono int
)
begin
    update encargado
    set nombre_encargado = p_nombre,
        apellido_encargado = p_apellido,
        fecha_nacimiento_encargado = p_fecha,
        direccion_encargado = p_direccion,
        telefono_encargado = p_telefono
    where id_encargado = p_id;
end $$

 delimiter $$
create procedure sp_eliminar_encargado(in p_id int)
begin
    delete from encargado
    where id_encargado = p_id;
end $$
 
 delimiter $$
create procedure sp_listar_encargado()
begin
    select * from encargado;
end $$

delimiter $$

create procedure sp_buscar_encargado_por_id(
    in p_id int
)
begin
    select *
    from encargado
    where id_encargado = p_id;
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


 -- -- Registros Grado ---
call sp_agregar_grado('Primero A', 1);
call sp_agregar_grado('Primero B', 2);
call sp_agregar_grado('Segundo A', 3);
call sp_agregar_grado('Segundo B', 4);
call sp_agregar_grado('Tercero A', 5);
call sp_agregar_grado('Tercero B', 6);
call sp_agregar_grado('Cuarto A', 7);
call sp_agregar_grado('Cuarto B', 8);
call sp_agregar_grado('Quinto A', 9);
call sp_agregar_grado('Quinto B', 10);

-- --- Registros Encargado ---
call sp_agregar_encargado('María', 'Gómez', '1985-03-12', 'Col. Centro, Calle 5', 5551234);
call sp_agregar_encargado('Juan', 'Pérez', '1978-07-25', 'Col. Norte, Av. Hidalgo', 5555678);
call sp_agregar_encargado('Ana', 'López', '1990-11-08', 'Col. Reforma, Calle 12', 5559012);
call sp_agregar_encargado('Carlos', 'Ramírez', '1982-01-19', 'Col. Sur, Av. Juárez', 5553456);
call sp_agregar_encargado('Laura', 'Martínez', '1995-09-30', 'Col. Centro, Calle 8', 5557890);
call sp_agregar_encargado('Pedro', 'Hernández', '1988-04-14', 'Col. Oriente, Av. 20', 5552345);
call sp_agregar_encargado('Sofía', 'Torres', '1992-06-21', 'Col. Poniente, Calle 3', 5556789);
call sp_agregar_encargado('Miguel', 'Castro', '1980-12-05', 'Col. Norte, Calle 15', 5551122);
call sp_agregar_encargado('Elena', 'Vargas', '1987-08-17', 'Col. Reforma, Av. Central', 5553344);
call sp_agregar_encargado('Diego', 'Mendoza', '1993-02-28', 'Col. Sur, Calle 10', 5555566);
