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
public class Consultas {
    public static final String preModulos = 
            "select mo.nombre, al.nombre , al.apellidoP, al.apellidoM, ma.notaFinal\n" +
"from modulo mo inner join modulo_alumno ma inner join alumno al on\n" +
"mo.codigo = ma.codigo_modulo and ma.codigo_alumno = al.expediente\n" +
"where mo.codigo = ? order by ma.notaFinal  ;";
    public static final String preProfesor = 
            "select p.nombre, p.apellidoP, p.apellidoM from\n" +
"alumno al inner join profesor p inner join modulo_alumno ma on\n" +
"al.expediente = ma.codigo_alumno and ma.codigo_modulo = p.codigo_modulo\n" +
"where al.expediente = ?;";
    
    public static final String numeroAlumnos = 
            "select mo.nombre,count(ma.codigo_alumno) as ord from\n" +
"modulo mo inner join modulo_alumno ma on mo.codigo = ma.codigo_modulo\n" +
"group by ma.codigo_modulo order by ord;";
}
