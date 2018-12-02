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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import practica.main.Main;

/**
 * clase que crea un dialogo con los apartados de una insercion
 * @author Javier Gonzalez Rives
 */
public class InsertModulo extends JDialog implements ActionListener,WindowListener{
    
    private Connection conexion;
    private final String  nombreTabla = "modulo";
    
    // compoenentes
    private JTextField txCodigo,txNombre;
    private JLabel lbCodigo,lbNombre;
    private JButton btAcpetar;
    /**
     * construcotor del dialogo
     * @param frame ventana principal
     */
    public InsertModulo(JFrame frame)throws SQLException{
        super(frame,true);
        // creacion de la conecxion
        this.conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
        // incicializacion de los elementos
        init();
        // pack
        pack();
        setLocation(frame.getLocation());
        setResizable(false);
    }
    /**
     * metodo que inicializa los elementos
     */
    private void init (){
        ///compoennetes
        // cuadros de texto
        txCodigo = new JTextField(10);
        txNombre = new JTextField(10);
        // etiquetas
        lbCodigo = new JLabel("codigo: ");
        lbNombre = new JLabel("nombre: ");
        // boton 
        btAcpetar = new JButton("aceptar");
        btAcpetar.setToolTipText("realizar insert");
        btAcpetar.addActionListener(this);
        // paneles contenedores
        JPanel pCodigo = new JPanel();
        JPanel pNombre = new JPanel();
        // cambio de layout
        BoxLayout bx = new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS);
        this.setLayout(bx);
        // annadir a los paneles
        pCodigo.add(lbCodigo);
        pCodigo.add(txCodigo);
        pNombre.add(lbNombre);
        pNombre.add(txNombre);
        // annadir al dialogo
        add(pCodigo);
        add(pNombre);
        add(btAcpetar);
    }
/////////////////////////////////////////////////////acciones
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            
            String  codigo = txCodigo.getText() ;
            String nombre = txNombre.getText().trim();
            // comprovaciones
            if(nombre.isEmpty() || codigo.isEmpty()){
                JOptionPane.showMessageDialog(this, "faltan campos por rellenar", "error", JOptionPane.ERROR_MESSAGE);
            }else{
                int numCodigo = Integer.parseInt(codigo);
                PreparedStatement stat = conexion.prepareStatement("insert into "+ nombreTabla +" values(?,?)");
                stat.setInt(1, numCodigo);
                stat.setString(2, nombre);
                
                if(stat.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(this, "insercion correcta","correcto",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(SQLException sql){
        }catch(NumberFormatException num){
            JOptionPane.showMessageDialog(this, "codigo solo admite numeros","error de formato",JOptionPane.ERROR_MESSAGE);
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
