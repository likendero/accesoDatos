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
public class Updates {
    public static final String preNombreAlumno = 
            "update  alumno set nombre = ?, delegado = ? where expediente = ?;";
    public static final String preNota = 
            "update modulo_alumno set modulo_alumno.notaFinal = ? where codigo_alumno = ? and codigo_modulo = ?;";
    public static final String preProfesorModulo = 
            "update profesor set codigo_modulo = null where codigo_modulo = ?;";
    
}
