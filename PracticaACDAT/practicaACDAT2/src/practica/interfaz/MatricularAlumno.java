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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class MatricularAlumno extends JDialog implements ActionListener,WindowListener{
    
    private Connection conexion;
    
    private JComboBox<String> cbAlumno;
    private JComboBox<String> cbAsignatura;
    private ArrayList<Integer> codAsignatura;
    private ArrayList<Integer> codAlumno;
    private JLabel lbAlumno;
    private JLabel lbModulo;
    
    
    private JButton btAcpetar;
    /**
     * construcotor del dialogo
     * @param frame ventana principal
     * @throws java.sql.SQLException
     */
    public MatricularAlumno(JFrame frame)throws SQLException{
        super(frame,true);
        // creacion de la conecxion
        this.conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
        // listas
        codAlumno = new ArrayList<>();
        codAsignatura = new ArrayList<>();
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
        // combos 
        combo();
        // etiquetas
        lbAlumno = new JLabel("alumno: ");
        lbModulo = new JLabel("asignatura: ");
        // boton 
        btAcpetar = new JButton("aceptar");
        btAcpetar.setToolTipText("realizar insert");
        btAcpetar.addActionListener(this);
        // paneles contenedores
        JPanel pAlumno = new JPanel();
        JPanel pModulo = new JPanel();
        // cambio de layout
        BoxLayout bx = new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS);
        this.setLayout(bx);
        // annadir a los paneles
        pAlumno.add(lbAlumno);
        pAlumno.add(cbAlumno);
        pModulo.add(lbModulo);
        pModulo.add(cbAsignatura);
        // annadir al dialogo
        add(pAlumno);
        add(pModulo);
        add(btAcpetar);
    }
    /**
     * metodo que prepara el combo box de alumnos
     */
    private void combo(){
        ArrayList<String> nombreAlumno = new ArrayList<>();
        ArrayList<String> nombreModulo = new ArrayList<>();
        try{
            // crear combo para el alumno
            // objeto para sentencia
            Statement stat = conexion.createStatement();
            // realizacion de la consulta
            ResultSet res = stat.executeQuery("select nombre,expediente from alumno;");
            // recorrido de los resultados
            while(res.next()){
                // almacenamiento
                String nombre = res.getString(1);
                int expediente = res.getInt(2);
                // control que el nombre del alumno no sea nulo
                if(nombre != null){
                    // se annaden a sus respectivas listas
                    nombreAlumno.add(nombre);
                    codAlumno.add(expediente);
                }
             
                }   
                // instanciacion del combo box
                cbAlumno = new JComboBox(nombreAlumno.toArray());
                // segunda consulta 
                res = stat.executeQuery("select * from modulo;");
                while(res.next()){
                    String nombre = res.getString(2);
                    int id = res.getInt(1);
                    if(nombre != null){
                        nombreModulo.add(nombre);
                        codAsignatura.add(id);
                    }
                }
                cbAsignatura = new JComboBox(nombreModulo.toArray());
        }catch(SQLException sql){
        }
    }
/////////////////////////////////////////////////////acciones
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            
            int alumno = codAlumno.get(cbAlumno.getSelectedIndex());
            int modulo = codAsignatura.get(cbAsignatura.getSelectedIndex());
            System.out.println(alumno);
            System.out.println(modulo);
            // comprovaciones
            
            CallableStatement stat = conexion.prepareCall("{call matricularAlumnos(?,?,?)}");
            
            stat.setInt(1, modulo);
            stat.setInt(2, alumno);
            stat.registerOutParameter(3, Types.INTEGER);
            

            if(!stat.execute()){
                int resultado = stat.getInt(3);
               
                if(resultado > 0){
                    JOptionPane.showMessageDialog(this, "matriculacion correcta","correcto",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "no se ha podido matricular","error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(SQLException sql){
            System.out.println("error");
            sql.printStackTrace();
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
