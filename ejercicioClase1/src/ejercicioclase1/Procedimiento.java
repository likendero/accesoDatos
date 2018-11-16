/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioclase1;

//import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author windiurno
 */
public class Procedimiento {
    private static final String conexion =
            "mysql";
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conec = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio_clase?","root","admin");
            // conexion
            conec.close();
        }catch(ClassNotFoundException cl){
            System.out.println("error");
        }catch(SQLException sq){
            System.out.println("error sql");
            sq.printStackTrace();
        }
    }
}
