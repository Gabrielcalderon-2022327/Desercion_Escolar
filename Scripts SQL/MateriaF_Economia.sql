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
create procedure sp_agregar_materiasf(in p_nombre varchar(60), in p_descripcion text, in p_fecha_alerta date, in p_id_maestro int, in p_id_estudiante int)
begin
    insert into MateriasF(nombre_materiaf, descripcion_materiaf, fecha_alerta_materiaf, fk_id_maestro, fk_id_estudiante)
    values (p_nombre, p_descripcion, p_fecha_alerta, p_id_maestro, p_id_estudiante);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_materiasf(in p_id int, in p_nombre varchar(60), in p_descripcion text, in p_fecha_alerta date, in p_id_maestro int, in p_id_estudiante int)
begin
    update MateriasF
    set nombre_materiaf = p_nombre,
        descripcion_materiaf = p_descripcion,
        fecha_alerta_materiaf = p_fecha_alerta,
        fk_id_maestro = p_id_maestro,
        fk_id_estudiante = p_id_estudiante
    where id_materiasf = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_materiasf(in p_id int)
begin
    delete from MateriasF
    where id_materiasf = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_materiasf()
begin
    select * from MateriasF;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_materiasf(in p_id int)
begin
    select *
    from materias_f
    where id_materiasf = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_agregar_economia(in p_ingresos double, in p_fecha date, in p_id_estudiante int)
begin
    insert into Economia(ingresos_economia, fecha_economia, fk_id_estudiante)
    values (p_ingresos, p_fecha, p_id_estudiante);
end $$
delimiter ;

delimiter $$
create procedure sp_editar_economia(in p_id int, in p_ingresos double, in p_fecha date, in p_id_estudiante int)
begin
    update Economia
    set ingresos_economia = p_ingresos,
        fecha_economia = p_fecha,
        fk_id_estudiante = p_id_estudiante
    where id_economia = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_eliminar_economia(in p_id int)
begin
    delete from Economia
    where id_economia = p_id;
end $$
delimiter ;

delimiter $$
create procedure sp_listar_economia()
begin
select * from Economia;
end $$
delimiter ;

delimiter $$
create procedure sp_buscar_economia(in p_id int)
begin
    select *
    from Economia
    where id_economia = p_id;
end $$
delimiter ;

-- Economía
call sp_agregar_Economia(2500.50,'2026-01-30', 1);
call sp_agregar_Economia(1200.75,'2026-01-30', 2);
call sp_agregar_Economia(3000.00,'2026-01-30', 3);
call sp_agregar_Economia(4500.00,'2026-01-30', 4);
call sp_agregar_Economia(2100.00,'2026-01-30', 5);