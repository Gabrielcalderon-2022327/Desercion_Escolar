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
    constraint foreign key (fk_id_usuario) references Usuario(id_usuario) on delete cascade
);

create table Grado (
    id_grado int primary key auto_increment,
    nombre_grado varchar (45) not null,
    fk_id_maestro int not null,
    constraint foreign key (fk_id_maestro) references Maestro(id_maestro) on delete cascade
);

 
create table Encargado (
    id_encargado int primary key auto_increment,
    nombre_encargado varchar (60) not null,
    apellido_encargado varchar (50) not null,
    fecha_nacimiento_encargado date not null,
    direccion_encargado varchar (60) not null,
    telefono_encargado int not null
);

create table Estudiante (
id_estudiante int primary key auto_increment,
nombre_estudiante varchar(45) not null,
apellido_estudiante varchar(45) not null,
fecha_nacimiento_estudiante date not null,
direccion_estudiante varchar (60) not null,
telefono_estudiante int not null,
fk_id_encargado int not null,
fk_id_grado int not null,
constraint foreign key (fk_id_encargado) references Encargado(id_encargado) on delete cascade,
constraint foreign key (fk_id_grado) references Grado(id_grado) on delete cascade
);


create table Asistencia (
id_asistencia int primary key auto_increment,
fecha_asistencia date not null,
estado_asistencia varchar(50) not null,
fk_id_estudiante int not null,
constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante) on delete cascade
);

create table MateriasF (
    id_materiasf int primary key auto_increment,
    nombre_materiaf varchar(60) not null,
    descripcion_materiaf text(100) not null,
    fecha_alerta_materiaf date not null,
    fk_id_maestro int not null,
    fk_id_estudiante int not null,
    constraint foreign key (fk_id_maestro) references Maestro(id_maestro) on delete cascade,
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante) on delete cascade
);

create table Economia (
    id_economia int primary key auto_increment,
    ingresos_economia double not null,
    fecha_economia date not null,
    fk_id_estudiante int not null,
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante) on delete cascade
);

create table Riesgo (
    id_riesgo int primary key auto_increment,
    nivel_riesgo varchar(45) not null,
    descripcion_riesgo text(200) not null,
    fk_id_estudiante int not null,
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante) on delete cascade
);
 
 
create table Alerta (
    id_alerta int primary key auto_increment,
    fecha_alerta date not null,
    tipo_alerta varchar(45) not null,
    incidente_alerta varchar(250) not null,
    fk_id_riesgo int not null,
	constraint foreign key (fk_id_riesgo) references Riesgo(id_riesgo) on delete cascade
);

-- --------------------------------------------------------------------------------------------------------------------PROCEDIMIENTOS ALMACENADOS
-- ---------------------------------------------PARTE 1
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

-- ---------------------------------------------PARTE 2
-- GRADO --
delimiter $$
create procedure sp_agregar_grado(
    in p_nombre varchar(45),
    in p_id_maestro int
)
begin
    insert into Grado(nombre_grado, fk_id_maestro)
    values (p_nombre, p_id_maestro);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_grado(
    in p_id int,
    in p_nombre varchar(45),
    in p_id_maestro int
)
begin
    update Grado
    set nombre_grado = p_nombre,
        fk_id_maestro = p_id_maestro
    where id_grado = p_id;
end $$
delimiter ;
 
delimiter $$
create procedure sp_eliminar_grado(in p_id int)
begin
    delete from Grado where id_grado = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_grado()
begin
    select * from Grado;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_grado_por_id(
    in p_id int
)
begin
    select * from Grado where id_grado = p_id;
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
    insert into Encargado(nombre_encargado, apellido_encargado,
        fecha_nacimiento_encargado, direccion_encargado, telefono_encargado)
    values (p_nombre, p_apellido, p_fecha, p_direccion, p_telefono);
end $$
delimiter ;

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
    update Encargado
    set nombre_encargado = p_nombre,
        apellido_encargado = p_apellido,
        fecha_nacimiento_encargado = p_fecha,
        direccion_encargado = p_direccion,
        telefono_encargado = p_telefono
    where id_encargado = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_encargado(in p_id int)
begin
    delete from encargado where id_encargado = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_encargado()
begin
    select * from Encargado;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_encargado_por_id(
    in p_id int
)
begin
    select * from Encargado where id_encargado = p_id;
end $$
delimiter ;

-- ---------------------------------------------PARTE 3
delimiter $$
create procedure sp_agregar_estudiante(
in p_nombre varchar(45),
in p_apellido varchar(45),
in p_fecha date,
in p_direccion varchar(60),
in p_telefono int,
in p_id_encargado int,
in p_id_grado int
)
begin
	insert into Estudiante(nombre_estudiante, apellido_estudiante, fecha_nacimiento_estudiante, direccion_estudiante,  telefono_estudiante, fk_id_encargado, fk_id_grado)
		values (p_nombre, p_apellido, p_fecha, p_direccion, p_telefono, p_id_encargado, p_id_grado);
end $$
delimiter;
 
delimiter $$
create procedure sp_editar_estudiante(
in p_id int,
in p_nombre varchar(45),
in p_apellido varchar(45),
in p_fecha date,
in p_direccion varchar(60),
in p_telefono int,
in p_id_encargado int,
in p_id_grado int
)
begin
	update Estudiante 
		set nombre_estudiante = p_nombre, 
		apellido_estudiante = p_apellido,
		fecha_nacimiento_estudiante = p_fecha,
		direccion_estudiante = p_direccion,
		telefono_estudiante = p_telefono,
		fk_id_encargado = p_id_encargado,
		fk_id_grado = p_id_grado
	where id_estudiante = p_id;
end $$
delimiter ;
 
 delimiter $$
create procedure sp_eliminar_estudiante(in p_id int)
begin
delete from Estudiante where id_estudiante = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_estudiante_id(in p_id int)
begin
	select * from Estudiante  where id_estudiante = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_estudiantes()
begin
	select * from Estudiante;
end $$
delimiter ;

-- Asistencia
delimiter $$
create procedure sp_agregar_asistencia(
in p_fecha date,
in p_estado varchar(50),
in p_id_estudiante int
)
begin
	insert into Asistencia(fecha_asistencia, estado_asistencia, fk_id_estudiante)
		values (p_fecha, p_estado, p_id_estudiante);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_asistencia(
in p_id int,
in p_fecha date,
in p_estado varchar(50),
in p_id_estudiante int
)
begin
	update Asistencia 
		set fecha_asistencia = p_fecha,
		estado_asistencia = p_estado,
		fk_id_estudiante = p_id_estudiante
	where id_asistencia = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_asistencia(in p_id int)
begin
	delete from Asistencia where id_asistencia = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_asistencia_id(in p_id int)
begin
select * from Asistencia where id_asistencia = p_id;
end $$
delimiter ;



delimiter $$
create procedure sp_listar_asistencias()
begin
select * from Asistencia;
end $$
delimiter ;

-- ---------------------------------------------PARTE 4
delimiter $$
create procedure sp_agregar_MateriasF(
    in p_nombre_materia varchar(60),
    in p_descripcion varchar(100),
    in p_fecha_alerta date,
    in p_id_maestro int,
    in p_id_estudiante int
)
begin
    insert into MateriasF(nombre_materiaf, descripcion_materiaf, fecha_alerta_materiaf, fk_id_maestro, fk_id_estudiante)
		values (p_nombre_materia, p_descripcion, p_fecha_alerta, p_id_maestro, p_id_estudiante);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_materiasf(
    in p_id_materia_f int,
    in p_nombre_materia varchar(60),
    in p_descripcion varchar(100),
    in p_fecha_alerta date,
    in p_id_maestro int,
    in p_id_estudiante int
)
begin
    update MateriasF
    set nombre_materiaf = p_nombre_materia,
        descripcion_materiaf = p_descripcion,
        fecha_alerta_materiaf = p_fecha_alerta,
        fk_id_maestro = p_id_maestro,
        fk_id_estudiante = p_id_estudiante
    where id_materiasf = p_id_materia_f;
end $$
delimiter ;

delimiter $$
create procedure sp_materiasf_eliminar(in p_id_materia_f int)
begin
    delete from MateriasF where id_materiasf = p_id_materia_f;
end $$
delimiter ;


delimiter $$
create procedure sp_listar_materiasf()
begin
    select * from MateriasF;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_materiasf(in p_id_materia_f int)
begin
    select * from MateriasF where id_materiasf = p_id_materia_f;
end $$
delimiter ;

delimiter $$
create procedure sp_agregar_economia(
    in p_ingresos double,
    in p_fecha date,
    in p_id_estudiante int
)
begin
    insert into Economia(ingresos_economia, fecha_economia, fk_id_estudiante)
		values (p_ingresos, p_fecha, p_id_estudiante);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_economia(
    in p_id_economia int,
    in p_ingresos double,
    in p_fecha date,
    in p_id_estudiante int
)
begin
    update Economia
    set ingresos_economia = p_ingresos,
        fecha_economia = p_fecha,
        id_estudiante = p_id_estudiante
    where id_economia = p_id_economia;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_economia(in p_id_economia int)
begin
    delete from Economia where id_economia = p_id_economia;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_economia()
begin
    select * from Economia;
end $$
delimiter ;


delimiter $$
create procedure sp_buscar_economia(in p_id_economia int)
begin
    select * from Economia where id_economia = p_id_economia;
end $$
delimiter ;

-- ---------------------------------------------PARTE 5
-- RIESGO  
-- Agregar 
delimiter $$
create procedure sp_agregar_riesgo(
    in p_nivel varchar(45),
    in p_descripcion varchar(200),
    in p_id_estudiante int
)
begin
    insert into Riesgo(nivel_riesgo, descripcion_riesgo, fk_id_estudiante)
    values (p_nivel, p_descripcion, p_id_estudiante);
end $$
delimiter ;

-- Editar
delimiter $$    
create procedure sp_editar_riesgo(
    in p_id int,
    in p_nivel varchar(45),
    in p_descripcion varchar(200),
    in p_id_estudiante int
)
begin
    update Riesgo
    set nivel_riesgo = p_nivel,
        descripcion_riesgo = p_descripcion,
        fk_id_estudiante = p_id_estudiante
    where id_riesgo = p_id;
end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminar_riesgo(in p_id int)
begin
    delete from Riesgo where id_riesgo = p_id;
end $$
delimiter ;

-- Listar
delimiter $$    
create procedure sp_listar_riesgo()
begin
    select * from Riesgo;
end $$
delimiter ; 

-- Buscar
delimiter $$
create procedure sp_buscar_riesgo(in p_id int)
begin
	select * from Riesgo where id_riesgo = p_id;
end $$
delimiter ;
     
-- ALERTA 
-- Agregar
delimiter $$
create procedure sp_agregar_alerta(
    in p_fecha date,
    in p_tipo varchar(45),
    in p_incidente varchar(250),
    in p_id_riesgo int
)
begin
    insert into Alerta(fecha_alerta, tipo_alerta, incidente_alerta, fk_id_riesgo)
    values (p_fecha, p_tipo, p_incidente, p_id_riesgo);
end $$
delimiter ;

-- Editar
delimiter $$
create procedure sp_editar_alerta(
    in p_id int,
    in p_fecha date,
    in p_tipo varchar(45),
    in p_incidente varchar(250),
    in p_id_riesgo int
)
begin
    update Alerta
    set fecha_alerta = p_fecha,
        tipo_alerta = p_tipo,
        incidente_alerta = p_incidente,
        fk_id_riesgo = p_id_riesgo
    where id_alerta = p_id;
end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminar_alerta(in p_id int)
begin
    delete from Alerta where id_alerta = p_id;
end $$
delimiter ;

-- Listar
delimiter $$
create procedure sp_listar_alerta()
begin
    select * from Alerta;
end $$
delimiter ;

-- Buscar
delimiter $$
create procedure sp_buscar_alerta(in p_id int)
begin
	select * from Alerta where id_alerta = p_id;
end $$
delimiter ;

-- --------------------------------------------------------------------------------------------------------------------REGISTROS
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

call sp_agregar_estudiante('Carlos', 'Gómez', '2011-05-20', 'Zona 10 ciudad', 44556677, 1, 1);
call sp_agregar_estudiante('Lucía', 'Fernández', '2010-11-12', 'Mixco', 33221100, 2, 2);
call sp_agregar_estudiante('Mateo', 'Morales', '2009-02-28', 'Villa Nueva', 55443322, 3, 3);
call sp_agregar_estudiante('Sofía', 'Pineda', '2010-07-15', 'Zona 18 ciudad', 66778899, 4, 4);
call sp_agregar_estudiante('Ricardo', 'Castillo', '2008-12-05', 'Santa Catarina Pinula', 22334455, 5, 5);
call sp_agregar_estudiante('Elena', 'Vásquez', '2011-01-30', 'Zona 2 ciudad', 99887766, 1, 2);
call sp_agregar_estudiante('Javier', 'Soto', '2010-04-10', 'San Miguel Petapa', 44112233, 2, 3);
call sp_agregar_estudiante('Mariana', 'Delgado', '2009-09-22', 'Zona 13 ciudad', 55667788, 3, 4);
call sp_agregar_estudiante('Sebastián', 'Guerra', '2008-06-18', 'Fraijanes', 33445566, 4, 5);
call sp_agregar_estudiante('Camila', 'Juárez', '2010-10-01', 'Zona 21 ciudad', 77889900, 5, 1);

call sp_agregar_asistencia('2026-02-05', 'presente', 6);
call sp_agregar_asistencia('2026-02-05', 'tardanza', 7);
call sp_agregar_asistencia('2026-02-06', 'presente', 8);
call sp_agregar_asistencia('2026-02-06', 'ausente', 9);
call sp_agregar_asistencia('2026-02-09', 'presente', 1);
call sp_agregar_asistencia('2026-02-09', 'presente', 2);
call sp_agregar_asistencia('2026-02-10', 'tardanza', 3);
call sp_agregar_asistencia('2026-02-10', 'presente', 4);
call sp_agregar_asistencia('2026-02-11', 'ausente', 5);
call sp_agregar_asistencia('2026-02-11', 'presente', 10);

call sp_agregar_Economia(2500.50,'2026-01-30', 1);
call sp_agregar_Economia(1200.75,'2026-01-30', 2);
call sp_agregar_Economia(3000.00,'2026-01-30', 3);
call sp_agregar_Economia(4500.00,'2026-01-30', 4);
call sp_agregar_Economia(2100.00,'2026-01-30', 5);
call sp_agregar_Economia(1800.00,'2026-02-01', 1);
call sp_agregar_Economia(2750.25,'2026-02-02', 2);
call sp_agregar_Economia(3200.80,'2026-02-03', 3);
call sp_agregar_Economia(1500.40,'2026-02-04', 4);
call sp_agregar_Economia(4100.60,'2026-02-05', 5);

call sp_agregar_MateriasF('Quimica', 'Bajo desempeño en laboratorio', '2026-02-13', 2, 1);
call sp_agregar_MateriasF('Ingles', 'Problemas de pronunciacion', '2026-02-14', 4, 2);
call sp_agregar_MateriasF('Programacion', 'Errores en logica de codigo', '2026-02-15', 7, 3);
call sp_agregar_MateriasF('Biologia', 'No presento examen final', '2026-02-16', 3, 4);
call sp_agregar_MateriasF('Arte', 'Falta de participacion', '2026-02-17', 6, 5);
call sp_agregar_MateriasF('Matematicas', 'Bajo rendimiento en algebra', '2026-02-10', 1, 1);
call sp_agregar_MateriasF('Ciencias', 'Falta de proyectos', '2026-02-10', 3, 3);
call sp_agregar_MateriasF('Fisica', 'No entrego tareas', '2026-02-11', 6, 4);
call sp_agregar_MateriasF('Lenguaje', 'Dificultad de lectura', '2026-02-11', 5, 5);
call sp_agregar_MateriasF('Historia', 'Falta de interes', '2026-02-12', 4, 2);

call sp_agregar_riesgo('Alto','Ha faltado muchas veces. Ha perdido muchas materias. Ingresos muy bajos', 1);
call sp_agregar_riesgo('Alto','Ha faltado muchas veces. Ha perdido muchas materias. Ingresos muy bajos', 2);
call sp_agregar_riesgo('Alto','Ha faltado muchas veces. Ha perdido muchas materias. Ingresos muy bajos', 3);
call sp_agregar_riesgo('Medio','Ha faltado muchas veces. Ha perdido muchas materias.', 4);
call sp_agregar_riesgo('Medio','Ha faltado muchas veces. Ingresos muy bajos', 5);
call sp_agregar_riesgo('Medio','Ha perdido muchas materias. Ingresos muy bajos', 6);
call sp_agregar_riesgo('Bajo','Ha faltado muchas veces.', 7);
call sp_agregar_riesgo('Bajo','Ha perdido muchas materias.', 8);
call sp_agregar_riesgo('Bajo','Ingresos muy bajos', 9);
call sp_agregar_riesgo('Crítico','Ha faltado muchas veces. Ha perdido muchas materias. Ingresos muy bajos. Riesgo de abandono inmediato', 10);

call sp_agregar_alerta('2026-02-10','Inasistencia','Sin reporte por 3 días', 1);
call sp_agregar_alerta('2026-02-11','Académica','Promedio debajo de 60', 2);
call sp_agregar_alerta('2026-02-12','Económica','Solicitud prórroga de pago', 5);
call sp_agregar_alerta('2026-02-13','Retiro','Trabajo de medio tiempo', 4);
call sp_agregar_alerta('2026-02-14','Salud','Justificación médica', 3);
call sp_agregar_alerta('2026-02-15','Inasistencia','Faltas consecutivas sin justificar', 6);
call sp_agregar_alerta('2026-02-16','Académica','Reprobación de dos materias', 7);
call sp_agregar_alerta('2026-02-17','Conducta','Reporte disciplinario', 9);
call sp_agregar_alerta('2026-02-18','Económica','Retraso en pago de colegiatura', 6);
<<<<<<< HEAD
<<<<<<< HEAD
call sp_agregar_alerta('2026-02-19','Psicológica','Cambio de comportamiento notable', 8);
=======
call sp_agregar_alerta('2026-02-19','Psicológica','Cambio de comportamiento notable', 8);

-- --------------------------------------------------------------------------------------------------------------------FUNCIONES
delimiter $$
create function fn_determinar_riesgo(f_riesgo varchar(45))
returns varchar(45)
reads sql data
begin
	if (f_riesgo = "Bajo") then
		return "Medio";
    end if;
    
    if (f_riesgo = "Medio") then
		return "Alto";
    end if;
    
    if (f_riesgo = "Alto") then
		return "Crítico";
    end if;
    
    if (f_riesgo = "Crítico") then
		return f_riesgo;
    end if;
    
    if (f_riesgo = "") then
		return "Bajo";
    end if;
end $$
delimiter ;
-- --------------------------------------------------------------------------------------------------------------------TRIGGERS
delimiter $$
create trigger tr_riesgoEconomia
after insert on Economia
for each row
begin
	declare idEstudiante int;
    declare ingresos double;
    declare nivelRiesgo varchar(45);
    declare descripcion text;
    declare nRiesgo int;
    
    set idEstudiante = new.fk_id_estudiante;
    set ingresos = new.ingresos_economia;
    set nivelRiesgo = "";
    set descripcion = "";
    
    if ((select count(*) from Riesgo where fk_id_estudiante = idEstudiante) > 0) then
		set nivelRiesgo = (select nivel_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
        set descripcion = (select descripcion_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
    end if;
    
    if (descripcion not like "%Ingresos muy bajos%") then
		set descripcion = (select concat(descripcion, " Ingresos muy bajos."));
    end if;
    set nivelRiesgo = fn_determinar_riesgo(nivelRiesgo);
    
    if (ingresos <= 3500.00) then
		if ((select count(*) from Riesgo where fk_id_estudiante = idEstudiante) > 0) then
			set nRiesgo = (select id_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
			call sp_editar_riesgo(nRiesgo, nivelRiesgo, descripcion, idEstudiante);
		else
			call sp_agregar_riesgo(nivelRiesgo, descripcion, idEstudiante);
		end if;
	end if;
end $$
delimiter ;

delimiter $$
create trigger tr_riesgoAsistencia
after insert on Asistencia
for each row
begin
	declare numeroDeInasistencias int;
    declare idEstudiante int;
    declare nivelRiesgo varchar(45);
    declare descripcion text;
    declare nRiesgo int;
    
    set idEstudiante = new.fk_id_estudiante;
    set numeroDeInasistencias = (select count(*) from Asistencia where fk_id_estudiante = idEstudiante and estado_asistencia = "ausente" and fecha_asistencia >= CURDATE() - interval 30 day);
    set nivelRiesgo = "";
    set descripcion = "";
    
    if ((select count(*) from Riesgo where fk_id_estudiante = idEstudiante) > 0) then
		set nivelRiesgo = (select nivel_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
        set descripcion = (select descripcion_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
    end if;
    
    if (descripcion not like  "%Ha faltado muchas veces%") then
		set descripcion = (select concat(descripcion, " Ha faltado muchas veces."));
    end if;
    set nivelRiesgo = fn_determinar_riesgo(nivelRiesgo);
    
    if (numeroDeInasistencias >= 10) then
		if ((select count(*) from Riesgo where fk_id_estudiante = idEstudiante) > 0) then
			set nRiesgo = (select id_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
			call sp_editar_riesgo(nRiesgo, nivelRiesgo, descripcion, idEstudiante);
		else
			call sp_agregar_riesgo(nivelRiesgo, descripcion, idEstudiante);
		end if;
    end if;
end $$
delimiter ;

delimiter $$
create trigger tr_riesgoMaterias
after insert on MateriasF
for each row
begin
	declare numeroDeMaterias int;
    declare idEstudiante int;
    declare nivelRiesgo varchar(45);
    declare descripcion text;
    declare nRiesgo int;
    
    set idEstudiante = new.fk_id_estudiante;
    set numeroDeMaterias = (select count(*) from MateriasF where fk_id_estudiante = idEstudiante);
    set nivelRiesgo = "";
    set descripcion = "";
    
    if ((select count(*) from Riesgo where fk_id_estudiante = idEstudiante) > 0) then
		set nivelRiesgo = (select nivel_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
        set descripcion = (select descripcion_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
    end if;
    
    if (descripcion not like  "%Ha perdido muchas materias%") then
		set descripcion = (select concat(descripcion, " Ha perdido muchas materias."));
    end if;
    set nivelRiesgo = fn_determinar_riesgo(nivelRiesgo);
    
    if(numeroDeMaterias >= 4) then
		if ((select count(*) from Riesgo where fk_id_estudiante = idEstudiante) > 0) then
			set nRiesgo = (select id_riesgo from Riesgo where fk_id_estudiante = idEstudiante);
			call sp_editar_riesgo(nRiesgo, nivelRiesgo, descripcion, idEstudiante);
		else
			call sp_agregar_riesgo(nivelRiesgo, descripcion, idEstudiante);
		end if;
    end if;
end $$
delimiter ;

>>>>>>> 3c84bceabbce306173aad04f1d9cd4a60fa59f31
=======
call sp_agregar_alerta('2026-02-19','Psicológica','Cambio de comportamiento notable', 8);
>>>>>>> a7964f992b64ed4a900de1650dc1d527c7717a06
