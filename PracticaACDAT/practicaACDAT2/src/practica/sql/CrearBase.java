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
package practica.sql;
import com.mysql.cj.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * clase que se encarga de crear la base de datos y sus tablas para
 * el funcionamiento del programa
 * @author Javier Gonzalez Rives
 */
public class CrearBase {
    private Connection conexion;
    
    /**
     * constructor
     * @param conexion conexion con la base de datos
     */
    public CrearBase(Connection conexion){
        super();
        // captura de la conexion
        this.conexion = conexion;
    }
    /**
     * metodo que comprueba si existe la base de datos,
     * 
     * @return true si existe false en otro caso 
     */
    public boolean existsData()throws SQLException{
        Statement consulta = conexion.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'instituto'");
        ResultSet result = consulta.getResultSet();
        
        if(result.first()){
            if(result.getString(1).equals("instituto")){
                return true;
            }
        }
        return false;
    }
   
}
