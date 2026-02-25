drop database if exists db_desercionescolar_in5cm;
create database db_desercionescolar_in5cm;
use db_desercionescolar_in5cm;

create table MateriasF (
    id_materiasf int primary key auto_increment,
    nombre_materiaf varchar(60) not null,
    descripcion_materiaf text(100) not null,
    fecha_alerta_materiaf date not null,
    fk_id_maestro int not null,
    fk_id_estudiante int not null,
    constraint foreign key (fk_id_maestro) references Maestro(id_maestro),
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante)
);

create table Economia (
    id_economia int primary key auto_increment,
    ingresos_economia double not null,
    fecha_economia date not null,
    fk_id_estudiante int not null,
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante)
);

delimiter $$
create procedure sp_materiasf_insertar(
    in p_nombre_materia varchar(60),
    in p_descripcion varchar(100),
    in p_fecha_alerta date,
    in p_id_maestro int,
    in p_id_estudiante int
)
begin
    insert into MateriasF(nombre_materia, descripcion, fecha_alerta, id_maestro, id_estudiante)
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
    set nombre_materia = p_nombre_materia,
        descripcion = p_descripcion,
        fecha_alerta = p_fecha_alerta,
        id_maestro = p_id_maestro,
        id_estudiante = p_id_estudiante
    where id_materia_f = p_id_materia_f;
end $$
delimiter ;

delimiter $$
create procedure sp_materiasf_eliminar(in p_id_materia_f int)
begin
    delete from MateriasF
    where id_materia_f = p_id_materia_f;
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
    select *
    from MateriasF
    where id_materia_f = p_id_materia_f;
end $$
delimiter ;

delimiter $$
create procedure sp_agregar_economia(
    in p_ingresos double,
    in p_fecha date,
    in p_id_estudiante int
)
begin
    insert into Economia(ingresos, fecha, id_estudiante)
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
    set ingresos = p_ingresos,
        fecha = p_fecha,
        id_estudiante = p_id_estudiante
    where id_economia = p_id_economia;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_economia(in p_id_economia int)
begin
    delete from Economia
    where id_economia = p_id_economia;
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
    select *
    from Economia
    where id_economia = p_id_economia;
end $$
delimiter ;


-- Economía
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

-- Materia fallida
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