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
public class Procedimientos {
    public static final String altaAlumnos = 
            "create PROCEDURE `numeroAlumnos`(out numero int)\n" +
"BEGIN\n" +
"select count(*) from alumno into numero;\n" +
"END";
    public static final String matricularAlumnos = 
            "create procedure `matricularAlumnos`\n" +
"(in idmodulo int,in idalumno int,out matriculado int)\n" +
"begin\n" +
"declare numMod int default 0;\n" +
"declare existeModulo int default 0;\n" +
"declare existeAlumno int default 0;\n" +
"declare ultimo int default 0;\n" +
"declare existeTupla int default 0 ;\n" +
"select count(*) from modulo_alumno \n" +
" where codigo_modulo = idmodulo into numMod;\n" +
"select count(*) from alumno \n" +
" where expediente = idalumno into existeAlumno;\n" +
"select count(*) from modulo\n" +
" where codigo = idmodulo into existeModulo ;\n" +
" select count(*) from modulo_alumno \n" +
" where codigo_alumno = idalumno and codigo_modulo = idmodulo into existeTupla;\n" +
"if numMod > 30 then\n" +
"	set matriculado = 0;\n" +
"else\n" +
"	if existeModulo > 0 and existeAlumno > 0 and existeTupla <= 0 then\n" +
"		select max(idModulo_alumno) from modulo_alumno into ultimo;\n" +
"		insert into modulo_alumno (idModulo_alumno,codigo_alumno,codigo_modulo)"
            + " values(ultimo+1,idalumno,idmodulo);\n" +
"        set matriculado = 1;\n" +
"        else\n" +
"			set matriculado = 0;\n" +
"    end if;\n" +
"end if;\n" +
"end";
}
