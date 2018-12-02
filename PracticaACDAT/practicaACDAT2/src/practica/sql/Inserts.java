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
public class Inserts {
    public static String[] inserts ={
        "insert into modulo values(1,'ACDAT');",
        "insert into modulo values(2,'PSP');",
        "insert into alumno values(1,'Javier','Gonzalez','Rives','1998-2-9',1);",
        "insert into alumno values(2,'Alicia','Tome','Ortega','1990-6-2',0);",
        "insert into alumno values(3,'Angel','Salas','Calvo','1999-4-5',0);",
        "insert into alumno values(4,'Juan Raul','Panyagua','Casado','1998-3-23',0);",
        "insert into modulo_alumno values(1,1,1);",
        "insert into modulo_alumno values(2,2,1);",
        "insert into modulo_alumno values(3,3,2);",
        "insert into modulo_alumno values(4,4,2);",
        "insert into profesor values(1,'Isabel','nuñez','nuñez','c/ san javier n32','950034432',2);",
        "insert into profesor values(2,'Manuel','Vazquez','Feijo','c/ Notredame n54 2ºB','950236798',2);",
        "insert into profesor values(3,'Sergismundo','Pico','Puertas','c/ los balcanes nº28 4º','950323323',1);"

    }; 
}
