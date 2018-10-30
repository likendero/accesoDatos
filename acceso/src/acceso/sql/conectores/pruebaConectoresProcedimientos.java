/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso.sql.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class pruebaConectoresProcedimientos {
    //
    /**
     * inicio del programa
     * @param args 
     */
    public static void main(String[] args) {
        // inicio del bloque de control
        try{
            // cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // conexion de la base de datos local
            String conexion = "jdbc:mysql://localhost:3306/empresaz?"
                    + "user=root&password=admin&useSSL=true"
                    +"&useTimezone=true&serverTimezone=GMT";
            // connection
            Connection con = DriverManager.getConnection(conexion);
            // creacion de la consulta
            Statement sentencia = con.createStatement();
            
            con.close();
            
        }catch(){
        
        }
    }
}
