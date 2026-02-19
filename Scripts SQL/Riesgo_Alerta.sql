drop database if exists db_desercionescolar_in5cm;
create database db_desercionescolar_in5cm;
use db_desercionescolar_in5cm;

create table Riesgo (
    id_riesgo int primary key auto_increment,
    nivel_riesgo varchar(45) not null,
    descripcion_riesgo text(200) not null,
    fk_id_estudiante int not null,
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante)
);
 
 
create table Alerta (
    id_alerta int primary key auto_increment,
    fecha_alerta date not null,
    tipo_alerta varchar(45) not null,
    incidente_alerta varchar(250) not null,
    fk_id_riesgo int not null,
	constraint foreign key (fk_id_riesgo) references Riesgo(id_riesgo)
);


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