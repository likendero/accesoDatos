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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
public class InsertProfesor extends JDialog 
implements ActionListener,WindowListener,KeyListener{
    
    private Connection conexion;
    private final String  nombreTabla = "profesor";
    
    // compoenentes
    private JTextField txRFC,txNombre,txApellidoP,txApellidoM,txDireccion,txTelefono;
    private JLabel lbRFC,lbNombre,lbApellidoP,lbApellidoM,lbDireccion,lbTelefono,lbModulo;
    private JComboBox cbAsignaturas;
    private ArrayList<Integer> idModulo;
    private JButton btAcpetar;
    /**
     * construcotor del dialogo
     * @param frame ventana principal
     */
    public InsertProfesor(JFrame frame)throws SQLException{
        super(frame,true);
        // creacion de la conecxion
        this.conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
        // ide modulo 
        idModulo = new ArrayList<Integer>();
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
        txRFC = new JTextField(10);
        txNombre = new JTextField(10);
        txApellidoP = new JTextField(10);
        txApellidoM = new JTextField(10);
        txDireccion = new JTextField(15);
        txTelefono = new JTextField(10);
        txTelefono.addKeyListener(this);
        // combo
        combo();
        // etiquetas
        lbRFC = new JLabel("RFC: ");
        lbNombre = new JLabel("nombre: ");
        lbApellidoP = new JLabel("Primer apellido:");
        lbApellidoM = new JLabel("Segundo apellido: ");
        lbDireccion = new JLabel("direccion: ");
        lbTelefono = new JLabel("telefono ");
        lbModulo = new JLabel("modulo: ");
        // boton 
        btAcpetar = new JButton("aceptar");
        btAcpetar.setToolTipText("realizar insert");
        btAcpetar.addActionListener(this);
        // paneles contenedores
        JPanel pCodigo = new JPanel();
        JPanel pNombre = new JPanel();
        JPanel pApellidoP = new JPanel();
        JPanel pApellidoM = new JPanel();
        JPanel pDireccion = new JPanel();
        JPanel pTelefono = new JPanel();
        JPanel pModulo = new JPanel();
        // cambio de layout
        BoxLayout bx = new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS);
        this.setLayout(bx);
        // annadir a los paneles
        pCodigo.add(lbRFC);
        pCodigo.add(txRFC);
        pNombre.add(lbNombre);
        pNombre.add(txNombre);
        pApellidoP.add(lbApellidoP);
        pApellidoP.add(txApellidoP);
        pApellidoM.add(lbApellidoM);
        pApellidoM.add(txApellidoM);
        pDireccion.add(lbDireccion);
        pDireccion.add(txDireccion);
        pTelefono.add(lbTelefono);
        pTelefono.add(txTelefono);
        pModulo.add(lbModulo);
        pModulo.add(cbAsignaturas);
        // annadir al dialogo
        add(pCodigo);
        add(pNombre);
        add(pApellidoP);
        add(pApellidoM);
        add(pDireccion);
        add(pTelefono);
        add(pModulo);
        add(btAcpetar);
    }
    /**
     * metodo que prepara el combo box
     * con las asignaturas que puede impartir un profesor
     */
    private void combo(){
        try{
            // creacion del objeto consultor
            Statement stat = conexion.createStatement();
            // consulta de los modulos existentes
            ResultSet res = stat.executeQuery("select * from modulo");
            // array lsit para almacenar los modulos
            ArrayList<String> asignaturas = new ArrayList<String>();
            // recorrido de los resultados
            while(res.next()){
                String nombre = res.getString("nombre");
                int codigo  = res.getInt("codigo");
                // comprobacion de la nulidad de nombre
                if(nombre != null){
                    // guardado de sus valores
                    asignaturas.add(nombre);
                    this.idModulo.add(codigo);
                }
            }
            // paso al combo box
            cbAsignaturas = new JComboBox(asignaturas.toArray());
        }catch(SQLException sql){
        }
    }
/////////////////////////////////////////////////////acciones
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // paso a texto
            String  RFC = txRFC.getText().trim();
            String nombre = txNombre.getText().trim();
            String ApellidoP = txApellidoP.getText().trim();
            String ApellidoM = txApellidoM.getText().trim();
            String Direccion = txDireccion.getText().trim();
            String Telefono = txTelefono.getText().trim();
            int codigo = idModulo.get(cbAsignaturas.getSelectedIndex());
            
            // comprovaciones campos vacios
            if(
                    nombre.isEmpty() ||
                    RFC.isEmpty() ||
                    ApellidoM.isEmpty() ||
                    ApellidoP.isEmpty() ||
                    Direccion.isEmpty() ||
                    Telefono.isEmpty() 
                    
                    ){
                JOptionPane.showMessageDialog(this, "faltan campos por rellenar", "error", JOptionPane.ERROR_MESSAGE);
            }else{
                
                PreparedStatement stat = conexion.prepareStatement("insert into "+ nombreTabla +" values(?,?,?,?,?,?,?)");
                // annadiendo los valores
                stat.setString(1, RFC);
                stat.setString(2, nombre);
                stat.setString(3, ApellidoP);
                stat.setString(4, ApellidoM);
                stat.setString(5, Direccion);
                stat.setString(6, Telefono);
                stat.setInt(7, codigo);
                if(stat.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(this, "insercion correcta","correcto",JOptionPane.INFORMATION_MESSAGE);
                }
                stat.close();
            }
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(this,"existe un error en alguno de los campos",
                    "error",JOptionPane.ERROR_MESSAGE);
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

    @Override
    public void keyTyped(KeyEvent e) {
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // control para solo numeros o + -
        if(e.getKeyChar() > '0' && e.getKeyChar() < '9' 
                && e.getKeyChar() == '+' && e.getKeyChar() == '-'
                || e.getKeyChar() == '\b'){
            
        }else{
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
