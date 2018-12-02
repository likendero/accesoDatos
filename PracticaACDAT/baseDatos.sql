drop database instituto;
create database if not exists instituto;
use instituto;
create user 'gestorInstituto'@'localhost' identified by 'gesPass';
grant all privileges on instituto.* to 'gestorInstituto'@'localhost';
-- creacion de tablas

create table if not exists modulo (
	codigo int primary key ,
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
    constraint  foreign key(codigo_modulo)references modulo(codigo)
    on delete restrict
    on update cascade
)engine = innodb
character set UTF8
collate utf8_spanish_ci;

create table if not exists alumno(
	expediente int primary key,
	nombre varchar(25),
    apellidoP varchar(25),
    apellidoM varchar(25),
    fechaNac date,
    delegado binary
)
engine = innodb
character set UTF8
collate utf8_spanish_ci;

create table if not exists modulo_alumno(
	idModulo_alumno int primary key,
    codigo_alumno int,
    codigo_modulo int,
    constraint  foreign key(codigo_alumno) references alumno(expediente)
    on delete cascade
    on update cascade,
    constraint  foreign key(codigo_modulo) references modulo(codigo)
    on delete restrict
    on update cascade
)
engine = innodb
character set UTF8
collate utf8_spanish_ci;

begin;
-- insercion modulos
insert into modulo values(1,'ACDAT');
insert into modulo values(2,'PSP');

-- insertar alumnos
insert into alumno values(1,'Javier','Gonzalez','Rives','1998-2-9',1);
insert into alumno values(2,'Alicia','Tome','Ortega','1990-6-2',0);
insert into alumno values(3,'Angel','Salas','Calvo','1999-4-5',0);
insert into alumno values(4,'Juan Raul','Panyagua','Casado','1998-3-23',0);
-- vincualar alumnos y modulos
insert into modulo_alumno values(1,1,1);
insert into modulo_alumno values(2,2,1);
insert into modulo_alumno values(3,3,2);
insert into modulo_alumno values(4,4,2);
-- profesores
insert into profesor values(1,'Isabel','nuñez','nuñez','c/ san javier n32','950034432',2);
insert into profesor values(2,'Manuel','Vazquez','Feijo','c/ Notredame n54 2ºB','950236798',2);
insert into profesor values(3,'Sergismundo','Pico','Puertas','c/ los balcanes nº28 4º','950323323',1);

commit;



