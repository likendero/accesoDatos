/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioclase1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class EjecutarProcedimiento {
    private static final String conexion =
            "jdbc:mysql://localhost:3306/ejercicio?user=root&password=admin&useTimezone=true&serverTimezone=GMT&SSL=true";
    private static String llamada = "{call alumnosSinCombocatoria()}";
   /**
     oppppppppppppppl           
     * @param args 
     */
    public static void main(String[] args) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            // creacion de la conexion
            Connection conec = DriverManager.getConnection(conexion);
            // creacion de la sentencia "llamable" para el procedimento
            CallableStatement sentencia = conec.prepareCall(llamada);
           // preparacion de la ejecucion
            sentencia.execute();
            
            ResultSet res = sentencia.getResultSet();
            
            while(res.next()){
                System.out.println("nomnre del alumno: " + res.getString(1) + " asignatura: " +res.getString(2) );
            }
            
            // cerrado de la conexion
            conec.close();
        }catch(ClassNotFoundException cl){
            System.out.println("error");
        }catch(SQLException sq){
            System.out.println("error sql");
            sq.printStackTrace();
        }
    }
}
