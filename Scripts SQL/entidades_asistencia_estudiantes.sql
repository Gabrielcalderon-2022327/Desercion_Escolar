drop database if exists desercion_escolar_IN5CM;
create database if not exists desercion_escolar_IN5CM;
use desercion_escolar_IN5CM;

create table Estudiante (
id_estudiante int primary key auto_increment,
nombre_estudiante varchar(45) not null,
apellido_estudiante varchar(45) not null,
fecha_nacimiento_estudiante date not null,
direccion_estudiante varchar (60) not null,
telefono_estudiante int not null,
fk_id_encargado int not null,
fk_id_grado int not null,
constraint foreign key (fk_id_encargado) references Encargado(id_encargado),
constraint foreign key (fk_id_grado) references Grado(id_grado)
);


create table Asistencia (
id_asistencia int primary key auto_increment,
fecha_asistencia date not null,
estado_asistencia varchar(50) not null,
fk_id_estudiante int not null,
constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante)
);

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
insert into Estudiante(nombre_estudiante, apellido_estudiante, 
fecha_nacimiento_estudiante, direccion_estudiante, 
telefono_estudiante, fk_id_encargado, fk_id_grado)
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
select * from Estudiante 
where id_estudiante = p_id;
end //

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
select * from Asistencia 
where id_asistencia = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_estudiantes()
begin
select * from Estudiante;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_asistencias()
begin
select * from Asistencia;
end $$
delimiter ;


-- Estudiantes
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

-- Asistencia
call sp_agregar_asistencia('2026-02-05', 'presente', 6);
call sp_agregar_asistencia('2026-02-05', 'tardanza', 7);
call sp_agregar_asistencia('2026-02-06', 'presente', 8);
call sp_agregar_asistencia('2026-02-06', 'ausente', 9);
call sp_agregar_asistencia('2026-02-09', 'presente', 10);
call sp_agregar_asistencia('2026-02-09', 'presente', 11);
call sp_agregar_asistencia('2026-02-10', 'tardanza', 12);
call sp_agregar_asistencia('2026-02-10', 'presente', 13);
call sp_agregar_asistencia('2026-02-11', 'ausente', 14);
call sp_agregar_asistencia('2026-02-11', 'presente', 15);