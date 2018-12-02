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

import com.sun.prism.j2d.J2DPipeline;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;
import practica.control.Acciones;
import practica.main.Main;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class Ventana extends JFrame implements ActionListener{
    private JButton btCrearProcedures;
    private JButton btActualizar;
    private JButton btInsertModulo;
    private JButton btInsertarAlumno;
    private JButton btInsertarProfesor;
    private JButton btMatricularALmuno;
    private JButton btSelectModulosNotas;
    private JButton btProfesorAlumno;
    private JButton btAlumnosModulo;
    private JButton btUpdateNombre;
    private JButton btUpdateNota;
    private JButton btDelAlumno;
    private JButton btDelModulo;
    /**
     * metodo conscructor de la ventana
     */
    public Ventana (){
        super("acciones sobre instituto");
        // definicion dimensiones de la ventana
        setBounds(100, 100, 400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }
    /**
     * metodo que inicializa los elementos de la ventana
     */
    private void init(){
        // inicializacion de los botones
        btCrearProcedures = new JButton("crear procedimientos");
        btActualizar = new JButton("Actualizar");
        btInsertModulo = new JButton("añadir un modulo");
        btInsertarAlumno = new JButton("añadir un alumno");
        btInsertarProfesor = new JButton("añadir un profesor");
        btMatricularALmuno = new JButton("Matricular Alumno");
        btSelectModulosNotas = new JButton("ver notas por modulo");
        btProfesorAlumno = new JButton("ver profesores de un alumno");
        btAlumnosModulo = new JButton("alumnos por modulo");
        btUpdateNombre = new JButton("actaulizar alumno");
        btUpdateNota = new JButton("actualzar nota");
        btDelAlumno = new JButton("borrar alumno");
        btDelModulo = new JButton("borrar Modulo");
        // annadir info
        btCrearProcedures.setToolTipText("crea los procedimientos almacenados");
        btActualizar.setToolTipText("comprueba si la base de datos requiere de actualizaciones");
        btInsertModulo.setToolTipText("añadir un nuevo modulo");
        btInsertarAlumno.setToolTipText("añadir un nuevo alumno");
        btInsertarProfesor.setToolTipText("añadir un nuevo profesor");
        btMatricularALmuno.setToolTipText("Matricular un alumno");
        btSelectModulosNotas.setToolTipText("ver las notas de los alumnos matriculados en un modulo");
        btProfesorAlumno.setToolTipText("ver los preofesores que dan clase a un alumno");
        btAlumnosModulo.setToolTipText("numero de alumnos que hay en cada modulo");
        btUpdateNombre.setToolTipText("cambiar nombre y estatus de delegado de un alumno");
        btUpdateNota.setToolTipText("actualziar la nota de un alumno en un modulo");
        btDelAlumno.setToolTipText("borrar un alumno");
        btDelModulo.setToolTipText("borrar un modulo (no debe contener alumnos)");
        
        // annadir acciones
        btCrearProcedures.addActionListener(this);
        btActualizar.addActionListener(this);
        btInsertModulo.addActionListener(this);
        btInsertarAlumno.addActionListener(this);
        btInsertarProfesor.addActionListener(this);
        btMatricularALmuno.addActionListener(this);
        btSelectModulosNotas.addActionListener(this);
        btProfesorAlumno.addActionListener(this);
        btAlumnosModulo.addActionListener(this);
        btUpdateNombre.addActionListener(this);
        btUpdateNota.addActionListener(this);
        btDelAlumno.addActionListener(this);
        btDelModulo.addActionListener(this);
        // panel contenendor
        JPanel contenedor = new JPanel();
        // add panel contenedor
        contenedor.add(btCrearProcedures);
        contenedor.add(btActualizar);
        contenedor.add(btInsertModulo);
        contenedor.add(btInsertarAlumno);
        contenedor.add(btInsertarProfesor);
        contenedor.add(btMatricularALmuno);
        contenedor.add(btSelectModulosNotas);
        contenedor.add(btProfesorAlumno);
        contenedor.add(btAlumnosModulo);
        contenedor.add(btUpdateNombre);
        contenedor.add(btUpdateNota);
        contenedor.add(btDelAlumno);
        contenedor.add(btDelModulo);
        // add ventana
        add(contenedor,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //  caso pulsar boton creacion de procedimientos
        if(e.getSource() == btCrearProcedures){
            Acciones.crearProcedimientos();
        }
        // caso boton actualizar
        if(e.getSource() == btActualizar){
            Acciones.Actualizar();
        }
        // inserts////
        try{
            // modulo
            if(e.getSource() == btInsertModulo){
                InsertModulo insertModulo = new InsertModulo(this);
                insertModulo.setVisible(true);
            }
            // Alumno
            if(e.getSource() == btInsertarAlumno){
                InsertAlumno insertAlumno = new InsertAlumno(this);
                insertAlumno.setVisible(true);
            }
            // profesor
            if(e.getSource() == btInsertarProfesor){
                InsertProfesor insertProfesor = new InsertProfesor(this);
                insertProfesor.setVisible(true);
            }
            //  matricular alumno 
            if(e.getSource() == btMatricularALmuno){
                // conexion de comprobacion
                Connection conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
                DatabaseMetaData dbmd = conexion.getMetaData();
                ResultSet res = dbmd.getProcedures(null, null, "matricularAlumnos");
                boolean existe = false;
                if(res.next()){
                    existe = true;
                }
                
                if(existe){
                    // incio de ventana
                    MatricularAlumno matricularAlumno = new MatricularAlumno(this);
                    matricularAlumno.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "es necesario añadir procedimientos", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
            /// consultas
            // notas
            if(e.getSource() == btSelectModulosNotas){
                Connection conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
                DatabaseMetaData dbmd = conexion.getMetaData();
                ResultSet res = dbmd.getColumns(null, null, "modulo_alumno", null);
                boolean control = false;
                while(res.next()){
                    String nombre = res.getString(4);
                    if(nombre.equals("notaFinal")){
                        control = true;
                    }
                }
                conexion.close();
                if(control){
                    SelectModulosNotas selectModulosNotas = new SelectModulosNotas(this);
                    selectModulosNotas.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "es necesario actualizar para acceder a esta opcion!!!");
                }
            }
            // profesores de un alumno
            if(e.getSource() == btProfesorAlumno){
                SelectProfesorAlumno selectProfesorAlumno = new SelectProfesorAlumno(this);
                selectProfesorAlumno.setVisible(true);
            }
            //al en mod
            if(e.getSource() == btAlumnosModulo){
                SelectNumeroAlumnos selectNumeroAlumnos = new SelectNumeroAlumnos(this);
                selectNumeroAlumnos.setVisible(true);
            }
            /// updates
            // nombre delegado 
            if(e.getSource() == btUpdateNombre){
                UpdateNombreDelegado updateNombreDelegado = new UpdateNombreDelegado(this);
                updateNombreDelegado.setVisible(true);
            }
            if(e.getSource() == btUpdateNota){
                 Connection conexion = DriverManager.getConnection(Main.conc,Main.usuario,Main.pass);
                DatabaseMetaData dbmd = conexion.getMetaData();
                ResultSet res = dbmd.getColumns(null, null, "modulo_alumno", null);
                boolean control = false;
                while(res.next()){
                    String nombre = res.getString(4);
                    if(nombre.equals("notaFinal")){
                        control = true;
                    }
                }
                conexion.close();
                if(control){
                    UpdateNota updateNota = new UpdateNota(this);
                    updateNota.setVisible(control);
                }else{
                    JOptionPane.showMessageDialog(this, "es necesario actualizar para acceder a esta opcion!!!");
                }
            }
            /// borrados
            // borrar alumno
            if(e.getSource() == btDelAlumno){
                DeleteAlumno deleteAlumno = new DeleteAlumno(this);
                deleteAlumno.setVisible(true);
            }
            // borrar un modulo
            if(e.getSource() == btDelModulo){
                DeleteModulo deleteModulo = new DeleteModulo(this);
                deleteModulo.setVisible(true);
            }
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(this, "Error en la conexion", 
                    "error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, sql.getMessage(), 
                    "error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
