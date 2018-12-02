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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import practica.main.Main;

/**
 * clase que crea un dialogo con los apartados de una insercion
 * @author Javier Gonzalez Rives
 */
public class InsertAlumno extends JDialog implements ActionListener,WindowListener{
    
    private Connection conexion;
    private final String  nombreTabla = "alumno";
    
    // compoenentes
    private JTextField txExpediente,txNombre,txApellidoP,txApellidoM;
    private JFormattedTextField ftFechaNacimiento;
    private JCheckBox chDelegado;
    private JLabel lbExpediente,lbNombre,lbApellidoP,lbApellidoM,lbFecha;
    private JButton btAcpetar;
    /**
     * construcotor del dialogo
     * @param frame ventana principal
     */
    public InsertAlumno(JFrame frame)throws SQLException{
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
        txExpediente = new JTextField(10);
        txNombre = new JTextField(10);
        txApellidoP = new JTextField(10);
        txApellidoM = new JTextField(10);
        cuadroFecha();
        // ch
        chDelegado =  new JCheckBox("delegado");
        // etiquetas
        lbExpediente = new JLabel("expediente: ");
        lbNombre = new JLabel("nombre: ");
        lbApellidoP = new JLabel("Primer apellido:");
        lbApellidoM = new JLabel("Segundo apellido: ");
        lbFecha = new JLabel("fecha de nacimiento");
        // boton 
        btAcpetar = new JButton("aceptar");
        btAcpetar.setToolTipText("realizar insert");
        btAcpetar.addActionListener(this);
        // paneles contenedores
        JPanel pCodigo = new JPanel();
        JPanel pNombre = new JPanel();
        JPanel pApellidoP = new JPanel();
        JPanel pApellidoM = new JPanel();
        JPanel pFechaNacimiento = new JPanel();
        JPanel pDelegado = new JPanel();
        // cambio de layout
        BoxLayout bx = new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS);
        this.setLayout(bx);
        // annadir a los paneles
        pCodigo.add(lbExpediente);
        pCodigo.add(txExpediente);
        pNombre.add(lbNombre);
        pNombre.add(txNombre);
        pApellidoP.add(lbApellidoP);
        pApellidoP.add(txApellidoP);
        pApellidoM.add(lbApellidoM);
        pApellidoM.add(txApellidoM);
        pFechaNacimiento.add(lbFecha);
        pFechaNacimiento.add(ftFechaNacimiento);
        pDelegado.add(chDelegado);
        // annadir al dialogo
        add(pCodigo);
        add(pNombre);
        add(pApellidoP);
        add(pApellidoM);
        add(pFechaNacimiento);
        add(pDelegado);
        add(btAcpetar);
    }
    /**
     * metodo que instancia el cuadro para la fecha
     */
    private void cuadroFecha(){
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = form.format(new Date());
        try{
            MaskFormatter mask = new MaskFormatter("####-##-##");
            ftFechaNacimiento = new JFormattedTextField(mask);
        }catch(ParseException par){
            par.printStackTrace();
        }
        ftFechaNacimiento.setToolTipText("fecha formato yyyy-MM-dd");
        ftFechaNacimiento.setText(fecha);
    }
/////////////////////////////////////////////////////acciones
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // paso a texto
            String  codigo = txExpediente.getText().trim();
            String nombre = txNombre.getText().trim();
            String ApellidoP = txApellidoP.getText().trim();
            String ApellidoM = txApellidoM.getText().trim();
            String fechaNac = ftFechaNacimiento.getText().trim();
            byte delegado = (chDelegado.isSelected()?(byte)1:(byte)0);
            // comprovaciones campos vacios
            if(
                    nombre.isEmpty() ||
                    codigo.isEmpty() ||
                    ApellidoM.isEmpty() ||
                    ApellidoP.isEmpty() ||
                    fechaNac.isEmpty()
                    ){
                JOptionPane.showMessageDialog(this, "faltan campos por rellenar", "error", JOptionPane.ERROR_MESSAGE);
            }else{
                int numCodigo = Integer.parseInt(codigo);
                PreparedStatement stat = conexion.prepareStatement("insert into "+ nombreTabla +"(expediente,nombre,apellidoP,apellidoM,fechaNac,delegado) values(?,?,?,?,?,?)");
                // annadiendo los valores
                stat.setInt(1, numCodigo);
                stat.setString(2, nombre);
                stat.setString(3, ApellidoP);
                stat.setString(4, ApellidoM);
                stat.setString(5, fechaNac);
                stat.setByte(6, delegado);
                if(stat.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(this, "insercion correcta","correcto",JOptionPane.INFORMATION_MESSAGE);
                }
                stat.close();
            }
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(this,"existe un error en alguno de los campos",
                    "error",JOptionPane.ERROR_MESSAGE);
        }catch(NumberFormatException num){
            JOptionPane.showMessageDialog(this, "expediente solo admite numeros","error de formato",JOptionPane.ERROR_MESSAGE);
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
