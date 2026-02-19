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