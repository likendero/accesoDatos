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
package practica.control;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import practica.main.Main;
import practica.sql.Alter;
import practica.sql.Procedimientos;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class Acciones {
    public static void crearProcedimientos(){
        // control para la conexion
        try{
            // creacion de la conexion con la base de datos
            Connection conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
            // captura de la meta data
            DatabaseMetaData dbmd = conexion.getMetaData();
            // captura de los procedimientos
            ResultSet result = dbmd.getProcedures(null, null, "%");
            // variables para controlar los procedimientos creados
            boolean altaAl = false;
            boolean matricular = false;
            // recorrido de los alumnos
            while(result.next()){
                String nombre = result.getString(3);
                if(nombre.equals("numeroAlumnos")){
                    altaAl = true;
                }
                if(nombre.equals("matricularAlumnos")){
                    matricular  = true;
                }
                System.out.println(result.getString(3));
            }
            result.close();
            
            // creacion del procedimiento si no existe
            if(!altaAl){
                Statement stat = conexion.createStatement();
                   stat.execute(Procedimientos.altaAlumnos);
                JOptionPane.showMessageDialog(null, "procedimiento altaAlumnos cereado",
                        "procedimiento creado",JOptionPane.INFORMATION_MESSAGE);
            }
            if(!matricular){
                Statement stat = conexion.createStatement();
                   stat.execute(Procedimientos.matricularAlumnos);
                JOptionPane.showMessageDialog(null, "procedimiento matricularAlumnos cereado",
                        "procedimiento creado",JOptionPane.INFORMATION_MESSAGE);
            }
            
            conexion.close();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
    }
    /**
     * metodo que actualzia la base de datos si es necesario
     */
    public static void Actualizar(){
        try{
            Connection conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
            // metadata
            Statement stat = conexion.createStatement();
            // captura de las columnas de la  tabla
            ResultSet resul = stat.executeQuery("show columns from modulo_alumno");
            boolean act1 = false;
            while(resul.next()){
                String nombre = resul.getString(1);
                if(nombre.equals("notaFinal")){
                    act1 = true;
                }
            }
            // ejecucion de modificaciones
            if(!act1){
                stat.executeUpdate(Alter.notaFinal);
                JOptionPane.showMessageDialog(null, "Actualizacion nota final realizada con exito",
                        "actualizaciones", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "no hay actualizaciones en la base de datos",
                        "actualizaciones", JOptionPane.INFORMATION_MESSAGE);
            }
            stat.close();
            conexion.close();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
    }
}
