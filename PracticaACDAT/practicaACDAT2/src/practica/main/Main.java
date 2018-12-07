/*
 * Copyright (C) 2018 likendero
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
package practica.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import practica.interfaz.Ventana;
import practica.sql.*;
/**
 * 
 * @author Javier Gonzalez Rives
 */
public class Main {
    public static String conc = "jdbc:mysql://localhost/instituto?useSSL=false&serverTimezone=Europe/Madrid";
    public static String usuario = "gestorInstituto";
    public static String pass = "gesPass";
    /**
     * metodo que prepara y muestra la ventana
     */
    private static void mostrar(){
        Ventana ven = new Ventana();
        ven.setVisible(true);
    }
    /**
     * inicio del programa
     * @param args 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try{
                    JOptionPane.showMessageDialog(null, "recuerda que para que la aplicacion funcione"
                            + "\n es necesario tener creada la base de datos instituto y el "
                            + "\n gestorInstituto.");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    prepararBase();
                    mostrar();
                }catch(ClassNotFoundException cl){
                    JOptionPane.showMessageDialog(null,"imposible conectar a la base de datos", 
                            "error", JOptionPane.ERROR_MESSAGE);
                    cl.printStackTrace();
                    System.exit(-1);
                }catch(Exception ex){
                }
                
            }
        });
    }
    /**
     * metodo que pide la contrasenna al usuario
     * 
     */
    public static void introContrasenna(){
        // dialogo para introducir la contrasenna
        pass = JOptionPane.showInputDialog(null, "introduce la contrasenna del usuario gestorInstituto",
                "intro", JOptionPane.DEFAULT_OPTION);
        // en el caso de cancelar se cierra el programa
        if(pass == null){
            System.exit(0);
        }
    }
    /**
     * metodo que prepara el programa para funcionar
     * con la base de datos
     */
    private static void prepararBase(){
        try{
            introContrasenna();
            // primera conexion de prueba
            Connection conexion = DriverManager.getConnection(conc,usuario,pass);
            DatabaseMetaData dbmt = conexion.getMetaData();
            ResultSet rm = dbmt.getTables(null, null, "%", null);
            
            int numtablas = 0;
            while(rm.next()){
                String nombreTabla = rm.getString(3);
                // comprobacion de la existencia de las tablas
                if(
                        nombreTabla.equals("modulo") ||
                        nombreTabla.equals("alumno") ||
                        nombreTabla.equals("profesor") ||
                        nombreTabla.equals("modulo_alumno")
                        ){
                    // se summa si se encuentra una de las tablas correspondiente
                    numtablas++;
                }
                
            }
            conexion.close();
            if(numtablas < 4){
                
                crearTablas();
                int inserts = JOptionPane.showConfirmDialog(null, "desea introducir datos de prueba", "inserts?", JOptionPane.YES_NO_OPTION);
                if(inserts == 0){
                    insertarRegistros();
                }
            }
        }catch(SQLException sql){
            
            if(!pass.isEmpty()){
                JOptionPane.showMessageDialog(null, "Error la contraseña puede no ser correcta"
                        + "\n o el usuario o la base de datos no existe","ERROR en la conexion",JOptionPane.ERROR_MESSAGE);
                introContrasenna();
            }else{
                System.exit(0);
            }
            //sql.printStackTrace();
        }
    }
    /**
     * metodo que crea las tablas dentro de la base de datos
     */
    private static void crearTablas(){
        try{
            // conexion con la base de datos instituto
            Connection conexion = DriverManager.getConnection(conc,usuario,pass);
            // creacion de las tablas
            Statement stat = conexion.createStatement();
            // ejecucion de la ceracion de tablas
            stat.execute(Tablas.MODULO);
            stat.execute(Tablas.PROFESOR);
            stat.execute(Tablas.ALUMNO);
            stat.execute(Tablas.MODULO_ALUMNO);
            // cerrado de los canales
            stat.close();
            conexion.close();
            
        }catch(SQLException sql){
            sql.printStackTrace();
            JOptionPane.showMessageDialog(null, "error en la creacion de tablas", "error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(-1);
        }
    }
    /**
     * metodo que realiza la insercion de las filas de ejemplo
     */
    private static void insertarRegistros(){
        try{
            // conexion
            Connection conexion = DriverManager.getConnection(conc,usuario,pass);
            // creacion del objeto de ejecucion
            Statement stat = conexion.createStatement();
            // bucle que ejecuta los inserts
            for(int i = 0; i < Inserts.inserts.length;i++){
                
                stat.executeUpdate(Inserts.inserts[i]);
            }
            stat.close();
            conexion.close();
            JOptionPane.showMessageDialog(null, "datos insertados con exito", "introduccion existosa", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException sql){
            
        }
    }
}
