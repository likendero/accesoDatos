create database if not exists instituto;

-- creacion de tablas

begin;

create table if not exists modulo (
	codigo int primary key,
    nombre varchar(45)
)
engine = innodb
character set UTF8
collate utf8_spanish_ci;

create table if not exists profesor(
	RFC char(15) primary key,
    nombre varchar(25),
    apellidoP varchar(25),
    apellidoM varchar(25),
    direccion varchar(25),
    telefono char(10),
    codigo_modulo int,
    constraint codigo_modulo foreign key(codigo_modulo)references modulo(codigo)
    on delete cascade
    on update cascade
)engine = innodb
character set UTF8
collate utf8_spanish_ci;






commit;

