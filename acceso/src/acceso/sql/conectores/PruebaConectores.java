
package acceso.sql.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import javax.sql.*;
/**
 *
 * @author windiurno
 */
public class PruebaConectores {
    /**
     * metodo principal, conexion a base de datos
     * @param args 
     */
    public static void main(String[] args) {
        try{
            // cargar el driver 
            Class.forName("com.mysql.cj.jdbc.Driver");
            // conexion a la base datos local host (solo la cadena)
            String conexion = "jdbc:mysql://localhost:3306/world?"
                    + "user=root&password=admin&useSSL=true"
                    +"&useTimezone=true&serverTimezone=GMT";
            
            // obtener conexion
            Connection con = DriverManager.getConnection(conexion);
            con.close();
        }catch(SQLException sq){
            System.out.println("error con sql");
            sq.printStackTrace();
        }catch(ClassNotFoundException cl){
            System.out.println("clase no encontrada");
            cl.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
