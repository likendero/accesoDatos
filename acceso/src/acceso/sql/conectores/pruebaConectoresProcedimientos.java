/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso.sql.conectores;


import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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
            // creacion de procedimiento
            CallableStatement pro = con.prepareCall("{call numero_empleados(?)}");
          
            // datos de salida
            pro.registerOutParameter(1, Types.INTEGER);
            // ejecutar
            pro.execute();
            // capturar result set
            //ResultSet resultSet  = pro.getResultSet();
            // siuiente
            //resultSet.first();
            // mostrar
            //System.out.println(resultSet.getInt(1));
            int numero = pro.getInt(1);
            System.out.println("el numero de empleados es de:" + numero);
            con.close();
            
        }catch(ClassNotFoundException cl){
            System.out.println("clase no encontrada");
        }catch(SQLException sq){
            System.out.println("error sql");
            sq.printStackTrace();
        }
    }
}
