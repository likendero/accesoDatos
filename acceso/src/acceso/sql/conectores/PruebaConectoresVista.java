
package acceso.sql.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.sql.*;
/**
 *
 * @author windiurno
 */
public class PruebaConectoresVista {
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
            Statement sentencia = con.createStatement();
            int i = sentencia.executeUpdate(
                "create or replace view totales(dep,dnombre,nemp,media) as" +
                " select d.dept_no, d.nombre,count(emp_no),avg(salario)" +
                " from departamentos d left join empleados e on e.dept_no=d.dept_no" +
                " group by d.dept_no,d.nombre;"
            );
            System.out.println("resultado " + i);
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
