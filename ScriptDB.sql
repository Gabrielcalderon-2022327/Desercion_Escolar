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

create table Orientador(
	id_orientador int primary key auto_increment,
    nombre_orientador varchar(50) not null,
    apellido_orientador varchar(50) not null,
    fecha_nacimiento date not null,
    telefono int not null,
    direccion varchar(100) not null,
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

create table Materias (
    id_materias int primary key auto_increment,
    nombre_materia varchar(60) not null,
    descripcion_materia text(100) not null,
    fk_id_maestro int not null,
    fk_id_grado int not null,
    constraint foreign key (fk_id_maestro) references Maestro(id_maestro) on delete cascade,
    constraint foreign key (fk_id_grado) references Grado(id_grado) on delete cascade
);

create table Calificaciones(
	id_calificaciones int primary key auto_increment,
    bimestre int not null,
    nota decimal(10,2) not null,
    fk_id_estudiante int not null,
    fk_id_materia int not null,
    constraint foreign key (fk_id_estudiante) references Estudiante(id_estudiante) on delete cascade,
    constraint foreign key (fk_id_materia) references Materias(id_materias) on delete cascade
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

create table Reporte(
	id_reporte int primary key auto_increment,
    descripcion long text not null,
    fecha_reporte date not null,
    fk_id_orientador int not null,
    fk_id_alerta int not null,
    constraint foreign key (fk_id_orientador) references Orientador(id_orientador) on delete cascade,
    constraint foreign key (fk_id_alerta) references Alerta(id_alerta) on delete cascade
)
-- --------------------------------------------------------------------------------------------------------------------PROCEDIMIENTOS ALMACENADOS
-- RIESGO  
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

-- ALERTA 
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
after insert on Calificaciones
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

delimiter $$
create trigger tr_alerta_insert
after insert on Riesgo
for each row
begin
	declare idRiesgo int;
    set idRiesgo = new.id_riesgo;
    
    call sp_agregar_alerta(curdate(),"Pendiente de investigacion","Pendiente de investigacion", idRiesgo);
end $$
delimiter ;

delimiter $$
create trigger tr_alerta_update
after update on Riesgo
for each row
begin
	declare idRiesgo int;
    set idRiesgo = new.id_riesgo;
    
    call sp_agregar_alerta(curdate(),"Pendiente de investigacion","Pendiente de investigacion", idRiesgo);
end $$
delimiter ;