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
package practica.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import practica.main.Main;
import practica.sql.Consultas;

/**
 *   metodo que prepara una ventana para realizar la consulta de modulos y notas
 * @author Javier Gonzalez Rives
 */
public class SelectProfesorAlumno extends JDialog
implements ActionListener,WindowListener{
    private Connection conexion;
    private JComboBox<String> cbAlumno;
    private ArrayList<Integer> expedientes_alumnos;
    private JButton btBuscar;
    private JTextArea taResultado;
    /**
     * constructor
     */
    public SelectProfesorAlumno(JFrame frame)throws SQLException{
        super(frame,true);
        setLocation(frame.getLocation());
        // creacion de la conexion 
        conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
        expedientes_alumnos = new ArrayList<>();
        // incnio
        init();
        // pack
        pack();
    }
    /**
     * metodo que prepara e inicia los componentes
     */
    private void init(){
        // compoenentes
        combo();
        // boton
        btBuscar = new JButton("buscar");
        btBuscar.setToolTipText("buscar las notas del modulo en los alumnnos");
        // cuadro de texto
        taResultado = new JTextArea(20, 50);
        taResultado.setEditable(false);
        // boton
        btBuscar.addActionListener(this);
        // paneles contenedores
        JPanel pSuperior = new JPanel();
        JPanel psCentro = new JPanel();
        // annadir a los paneles
        pSuperior.add(cbAlumno);
        pSuperior.add(btBuscar);
        psCentro.add(taResultado);
        // add ventana
        add(pSuperior,BorderLayout.PAGE_START);
        add(psCentro,BorderLayout.CENTER);
    }
    /**
     * metodo para definir el combo box
     */
    private void combo(){
        try{
            ArrayList<String> nombres = new ArrayList<>();
            Statement stat = conexion.createStatement();
            ResultSet res = stat.executeQuery("select expediente,nombre from alumno;");
            // recorrido de la consulta
            while(res.next()){
                String nombre = res.getString(2);
                int cod = res.getInt(1);
                if(nombre != null){
                    // captura de los valores
                    nombres.add(nombre);
                    expedientes_alumnos.add(cod);
                }
            }
            cbAlumno = new JComboBox(nombres.toArray());
            stat.close();
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(this, "error en la busqueda de modulos","error",JOptionPane.ERROR_MESSAGE);
        }
    }
    // acciones ///////////////////////////////////////////////////////////////
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            int codigoBusqueda = expedientes_alumnos.get(cbAlumno.getSelectedIndex());
            String salida = "nombre\tapellido1\tapellido2";
            // creacion de la consulta
            PreparedStatement stat = conexion.prepareStatement(Consultas.preProfesor);
            // introduccion del valor
            stat.setInt(1, codigoBusqueda);
            ResultSet res = stat.executeQuery();
            while(res.next()){
               
                String nombre = res.getString(1);
                String apellido1 = res.getString(2);
                String apellido2 = res.getString(3);
               
                 if(nombre != null){
                    salida += '\n' +  nombre + '\t';
                }
                else{
                    salida +=  "null" + '\t';
                }
                  if(apellido1 != null){
                    salida += apellido1 + '\t';
                }
                else{
                  salida += "null\t";
                }
                if(apellido2 != null){
                    salida +=  apellido2 + '\t';
                }
                else{
                  salida += "null\t";
                }
               
            }
            taResultado.setText(salida);
            stat.close();
        }catch(SQLException sql){
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Ha sucedido un error","error",JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try{
            conexion.close();
        }catch(SQLException sql){
            
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
