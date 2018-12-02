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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import practica.main.Main;
import practica.sql.Deletes;
import practica.sql.Updates;

/**
 *  creacion de una ventana para actualizar el nombre de un alumno
 * @author Javier Gonzalez Rives
 */
public class DeleteModulo extends JDialog
implements ActionListener, WindowListener{
    private Connection conexion;
    private JTextField txModulo;
    
    private JLabel lbCodigoModulo;
    private JButton btBorrar;
    /**
     * constructor
     */
    public DeleteModulo(JFrame frame)throws SQLException{
        super(frame,true);
        // conexion 
        conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
        setLocation(frame.getLocation());
        //intit
        init();
        // pack
        pack();
    }
    /**
     * metodo que inicializa 
     */
    private void init(){
        // componenetes
        // textos
        txModulo = new JTextField(5);
       
        // bt
        btBorrar = new JButton("borrar");
        btBorrar.addActionListener(this);
        // etiquetas
        lbCodigoModulo = new JLabel("Codigo Modulo: ");
        
        // creacion paneles contenedores
        JPanel pCentro = new JPanel();
        JPanel pPie = new JPanel();
        // annadi a paneles
        pCentro.add(lbCodigoModulo);
        pCentro.add(txModulo);
        pPie.add(btBorrar);
        // adds
        add(pCentro,BorderLayout.CENTER);
        add(pPie,BorderLayout.PAGE_END);
    }
    // ACCIONES/////////////////////////////////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //  captura de los valores
            int codigo = Integer.parseInt(txModulo.getText().trim());
            // creacion de sentencia para comprobar alumnos
            PreparedStatement compStat = conexion.prepareStatement("select count(*) from modulo_alumno where codigo_modulo = ?;");
            compStat.setInt(1, codigo);
            ResultSet res = compStat.executeQuery();
            res.first();
            int numeroAl = res.getInt(1);
            compStat.close();
            if(numeroAl <= 0){
                // elminar el modulo de los profesores que lo dan
                PreparedStatement stat1 = conexion.prepareStatement(Updates.preProfesorModulo);
                stat1.setInt(1, codigo);
                stat1.execute();
                // BORRADO
                PreparedStatement stat = conexion.prepareStatement(Deletes.preModulo);
                stat.setInt(1, codigo);
                // ejecion
                
                if(stat.executeUpdate() > 0){
                    // caso correcto
                    JOptionPane.showMessageDialog(this, "borrado correcto", "correcto", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "no se ha realizado ningun borrado", "fallo", JOptionPane.INFORMATION_MESSAGE);
                }
                // caso que aun queden alumnos
                stat.close();
            }else{
                JOptionPane.showMessageDialog(this, "quedan alumnos matriculados", "Error",JOptionPane.ERROR_MESSAGE);
            }
            

        }catch(SQLException sql){
        }catch(NumberFormatException nm){
            JOptionPane.showMessageDialog(this, "Expediente solo puede contener numeros","error",JOptionPane.ERROR_MESSAGE);
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
