/*
 * Copyright (C) 2018 Javier Gonzalez Rives
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package practica.sql;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class Tablas {
    public static final String MODULO = 
            "create table if not exists modulo (\n" +
"	codigo int primary key,\n" +
"    nombre varchar(45)\n" +
")\n" +
"engine = innodb\n" +
"character set UTF8\n" +
"collate utf8_spanish_ci;";
    public static final String PROFESOR = 
            "create table if not exists profesor(\n" +
"	RFC char(15) primary key,\n" +
"    nombre varchar(25),\n" +
"    apellidoP varchar(25),\n" +
"    apellidoM varchar(25),\n" +
"    direccion varchar(25),\n" +
"    telefono char(10),\n" +
"    codigo_modulo int,\n" +
"    constraint  foreign key(codigo_modulo)references modulo(codigo)\n" +
"    on delete restrict\n" +
"    on update cascade\n" +
")engine = innodb\n" +
"character set UTF8\n" +
"collate utf8_spanish_ci;";
    public static final String ALUMNO = 
            "create table if not exists alumno(\n" +
"	expediente int primary key,\n" +
"	nombre varchar(25),\n" +
"    apellidoP varchar(25),\n" +
"    apellidoM varchar(25),\n" +
"    fechaNac date,\n" +
"    delegado binary\n" +
")\n" +
"engine = innodb\n" +
"character set UTF8\n" +
"collate utf8_spanish_ci;";
    public static final String MODULO_ALUMNO = 
            "create table if not exists modulo_alumno(\n" +
"	idModulo_alumno int primary key,\n" +
"    codigo_alumno int,\n" +
"    codigo_modulo int,\n" +
"    constraint  foreign key(codigo_alumno) references alumno(expediente)\n" +
"    on delete cascade\n" +
"    on update cascade,\n" +
"    constraint  foreign key(codigo_modulo) references modulo(codigo)\n" +
"    on delete restrict\n" +
"    on update cascade\n" +
")\n" +
"engine = innodb\n" +
"character set UTF8\n" +
"collate utf8_spanish_ci;";
}
