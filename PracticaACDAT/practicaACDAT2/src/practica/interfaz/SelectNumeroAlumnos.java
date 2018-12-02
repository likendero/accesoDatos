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

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import practica.main.Main;
import practica.sql.Consultas;

/**
 *   metodo que prepara una ventana para realizar la consulta de modulos y notas
 * @author Javier Gonzalez Rives
 */
public class SelectNumeroAlumnos extends JDialog
implements ActionListener,WindowListener{
    private Connection conexion;
    private JButton btBuscar;
    private JTextArea taResultado;
    /**
     * constructor
     */
    public SelectNumeroAlumnos(JFrame frame)throws SQLException{
        super(frame,true);
        setLocation(frame.getLocation());
        // creacion de la conexion 
        conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
        // incnio
        init();
        // pack
        pack();
    }
    /**
     * metodo que prepara e inicia los componentes
     */
    private void init(){
        
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
        pSuperior.add(btBuscar);
        psCentro.add(taResultado);
        // add ventana
        add(pSuperior,BorderLayout.PAGE_START);
        add(psCentro,BorderLayout.CENTER);
    }
    
    // acciones ///////////////////////////////////////////////////////////////
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            
            String salida = "modulo\tnumero de alumnos";
            // creacion de la consulta
            PreparedStatement stat = conexion.prepareStatement(Consultas.numeroAlumnos);
            // introduccion del valor
           
            ResultSet res = stat.executeQuery();
            while(res.next()){
               
                String nombre = res.getString(1);
                int cantidad = res.getInt(2);
                
               
                 if(nombre != null){
                    salida += '\n' +  nombre + '\t';
                }
                else{
                    salida +=  "null" + '\t';
                }
                  salida +=   cantidad;
               
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
