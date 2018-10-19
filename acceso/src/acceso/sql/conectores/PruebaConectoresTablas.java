
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
public class PruebaConectoresTablas {
    // sentencia para crear departamentos
    private static String departamentosCreacion =
"create table if not exists departamentos(" +
    "dept_no tinyint(2) primary key," +
    "nombre varchar(15)," +
    "loc varchar(15)" +
")engine=innodb;";
    // sentencua para crear empleados
    private static String empleadosCreacion = "create table if not exists empleados(" +
"	emp_no smallint primary key," +
"    apellido varchar(10)," +
"    oficio varchar(10)," +
"    dir smallint(6)," +
"    fecha_alt date," +
"    salario float(6,2)," +
"    comision float(6,2)," +
"    dept_no tinyint(2)" +
"    foreign key(dept_no) references(departamentos.dept_no)" +
"    on delete cascade" +
"    on update cascade" +
")engine=innodb;";
    private static String departamentosInsert = "insert into departamentos values " +
"(10,'CONTABILIDAD','SEVILLA')," +
"(20,'INVESTIGACION','MADIRD')," +
"(30,'VENTAS','BARCELONA')," +
"(40,'PRODUCCION','BILBAO');";
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
            
            // introduccion de sentencias
            Statement sentencia = con.createStatement();
            
            // primera sentencia
            sentencia.execute(departamentosCreacion);
            System.out.println("tabla creada");
            // introduccion de filas
            sentencia.execute(departamentosInsert);
            System.out.println("insert realizado");
            // fin de la conexion
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
