
package acceso.sql.conectores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.sql.*;
/**
 *
 * @author windiurno
 */
public class PruebaConectoresSelect {
    private static String consultaDepartamentos = 
            "select * from departamentos";
    /**
     * metodo principal, conexion a base de datos
     * @param args 
     */
    public static void main(String[] args) {
        try{
            // cargar el driver 
            Class.forName("com.mysql.cj.jdbc.Driver");
            // conexion a la base datos local host (solo la cadena)
            String conexion = "jdbc:mysql://localhost:3306/empresaz?"
                    + "user=root&password=admin&useSSL=true"
                    +"&useTimezone=true&serverTimezone=GMT";
            
            // obtener conexion
            Connection con = DriverManager.getConnection(conexion);
            // creacion de sentencia
            Statement sentencia = con.createStatement();
            // variable para capturar resultado
            ResultSet resultado = null;
            // realizacion y guardado 
            resultado = sentencia.executeQuery(consultaDepartamentos);
            //resultado
            while(resultado.next()){
                System.out.println("clave dep = " + resultado.getInt("dept_no"));
                System.out.println("clave nombre = " + resultado.getString("nombre"));
                System.out.println("clave localizacion = " + resultado.getString("loc"));
            }
            
            
            resultado.close();
            sentencia.close();
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
