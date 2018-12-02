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
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import practica.main.Main;
import practica.sql.Updates;

/**
 *  creacion de una ventana para actualizar el nombre de un alumno
 * @author Javier Gonzalez Rives
 */
public class UpdateNota extends JDialog
implements ActionListener, WindowListener{
    private Connection conexion;
    private JTextField txExpediente;
    private JTextField txModulo;
    private JSpinner spNota;
    private JLabel lbExpediente, lbModulo, lbNota ;
    private JButton btActualizar;
    /**
     * constructor
     */
    public UpdateNota(JFrame frame)throws SQLException{
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
        txExpediente = new JTextField(5);
        txModulo = new JTextField(10);
        // sp
        SpinnerNumberModel sp = new SpinnerNumberModel(1,1,10,1);
        spNota = new JSpinner(sp);
        // bt
        btActualizar = new JButton("Actualizar");
        btActualizar.addActionListener(this);
        // etiquetas
        lbExpediente = new JLabel("expediente: ");
        lbModulo = new JLabel("modulo: " );
        lbNota = new JLabel("nota: ");
        // creacion paneles contenedores
        JPanel pCentro = new JPanel();
        JPanel pPie = new JPanel();
        // annadi a paneles
        pCentro.add(lbExpediente);
        pCentro.add(txExpediente);
        pCentro.add(lbModulo);
        pCentro.add(txModulo);
        pCentro.add(lbNota);
        pCentro.add(spNota);
        pPie.add(btActualizar);
        // adds
        add(pCentro,BorderLayout.CENTER);
        add(pPie,BorderLayout.PAGE_END);
    }
    // ACCIONES/////////////////////////////////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //  captura de los valores
            int codigo = Integer.parseInt(txExpediente.getText().trim());
            int modulo = Integer.parseInt(txModulo.getText().trim());
            int nota = (Integer) spNota.getValue();
            
            // creacion de sentencia
            PreparedStatement stat = conexion.prepareStatement(Updates.preNota);
            stat.setInt(1, nota);
            stat.setInt(2, codigo);
            stat.setInt(3, modulo);
            // ejecion
            if(stat.executeUpdate() > 0){
                // caso correcto
                JOptionPane.showMessageDialog(this, "actualizacion correcta", "correcto", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "no se ha realizado ninguna actualizacion", "fallo", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch(SQLException sql){
            
        }catch(NumberFormatException nm){
            JOptionPane.showMessageDialog(this, "Expediente y modulo solo puede contener numeros","error",JOptionPane.ERROR_MESSAGE);
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
