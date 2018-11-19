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
import java.sql.Statement;
import javax.sql.StatementEvent;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class Procedimiento {
   private static final String conexion =
            "jdbc:mysql://localhost:3306/ejercicio?user=root&password=admin&useTimezone=true&serverTimezone=GMT&SSL=true";
   private static String procedure = 
           "create procedure alumnosSinCombocatoria() begin select al.nombre,asig.nombre from ejercicio.alumno al inner join ejercicio.asignatura asig inner join ejercicio.matricula mat on mat.dni = al.dni and mat.AS = asig.AS where mat.nconvocatoria = 4 and mat.nota < 5   ; end";
    /**
     oppppppppppppppl           
     * @param args 
     */
    public static void main(String[] args) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            // creacion de la conexion
            Connection conec = DriverManager.getConnection(conexion);
            Statement sentencia = conec.createStatement();
            sentencia.execute(procedure);
            
           
            
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
