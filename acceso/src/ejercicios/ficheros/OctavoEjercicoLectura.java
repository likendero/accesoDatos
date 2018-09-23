/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author likendero
 */
public class OctavoEjercicoLectura {
    private static File directorio = new File("AleatorioEmple.dat");
    private static Scanner key = new Scanner(System.in);
    private static final int TAMANNO = 63;
    private static int numRegistros = 0;
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        
        try{
            System.out.println(leerRegistro(seleccion()));
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    /**
     * metodo que lee un registro indicado por el usuario
     * @return 
     */
    private static String leerRegistro(int registro){
        String salida = "";
        try{
            // creacion del flujo
            RandomAccessFile lector = new RandomAccessFile(directorio, "r");
            // al registro se le resta 1 ya que el primero empieza en 0
            registro -= 1;
            // movimiento del puntero
            lector.seek(TAMANNO*registro);
            // lectura
            salida = lector.readInt()+ " " +  lector.readUTF() + " " + lector.readUTF()
                    + " " + lector.readInt() ;
        }catch(IOException io){
            System.out.println("error en la lectura");
        }
        return salida;
    }
    /**
     * metodo que calcula el numero de registros en el fichero
     * @return 
     */
    public static int numeroRegistros(){
        int vuelta = 0;
        try{
            // creacion del flujo
            RandomAccessFile lector = new RandomAccessFile(directorio, "r");
            // guardado del numero de registros
            vuelta = (int)lector.length()/TAMANNO;
            lector.close();
        }catch(IOException io){
            System.out.println("error lectura");
            io.printStackTrace();
        }
        return vuelta;
    }
    /**
     * metodo que pregunta al usuario que desea ver
     * @return 
     */
    public static int seleccion(){
        // variable para bucle
        boolean control = false;
        int seleccion = 0;
        numRegistros =numeroRegistros();
        do{
            try{
                // mensaje al usuario
                System.out.println("el numero de registros es " + numRegistros);
                System.out.println("cual desea ver?(empieza en 1)");
                // introduccion del usuario
                seleccion = key.nextInt();
                // validacion de la introduccion
                if(seleccion > 0 && seleccion <= numRegistros){
                    // fin del bucle
                    control = true;
                }else{
                    // ensaje indicando el error
                    System.out.println("eleccion no valida");
                }
            }catch(InputMismatchException in){
                System.out.println("error en la introduccion");
                key.nextLine();
            }
        }while(!control);
        return seleccion; 
    }
        
    
}
