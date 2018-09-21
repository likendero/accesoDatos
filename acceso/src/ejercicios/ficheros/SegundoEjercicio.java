/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;

import java.io.File;
import java.util.Scanner;

/**
 *  listar todos los directorios y sub directorios
 * @author likendero
 */
public class SegundoEjercicio {
     // variable que sirve para almacenar el directorio 
    private static File directorio = null;
    // variable de tipo scanner para la introduccion por terminal
    private static Scanner key = new Scanner(System.in);
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        boolean control = false;
        File aux = null;
        do{
            try{
                directorio = FicherosTerminal.introDirectorio(key);
                // recorrido de los elementos encontrados en el directorio
                for(String i: directorio.list()){
                    System.out.println(i);
                    aux = new File(directorio.getAbsolutePath()+"/"+i);
                    if( aux.isDirectory()){
                        for(String j: aux.list()){
                            System.out.println("\t " + j);
                        }
                    }
                }
                control = true;
            // en el caso que el directorio sig siendo nulo
            }catch(NullPointerException nu){
                System.out.println("error en la ejecucion");
                nu.printStackTrace();
            // control de posibles errores
            }catch(Exception ex){
                System.out.println("error en la introduccion");
                ex.printStackTrace();
            }
        }while(!control);
    }
}
