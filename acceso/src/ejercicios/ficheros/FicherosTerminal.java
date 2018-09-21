/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.util.Scanner;
import java.io.File;
/**
 *  clase que contiene metodos con los que trabajar en terminal
 * @author likendero
 */
public class FicherosTerminal {
    
    /**
     * metodo que devulve un File con el directorio introducido
     * 
     */
    public static File introDirectorio(Scanner key)throws Exception{
        /*
        variables:
        aux objeto File que comprueba si el directorio es correcto
        control que gestiona el bucle
        */
        
        File aux = null;
        boolean control = false;
        // inicio del bucle
        do{
            System.out.println("introduzca un directorio: ");
            // sen introduce por terminale el directorio en la variable File
            aux = new File(key.next());
            // se comprueba si lo introducido es un directorio como tal
            if(!aux.isDirectory()){
                // en el caso que no sea asi se manda un mensaje por pantalla 
                // y el bucle continua
                System.out.println("no es un directorio valido");
            }
            else{
                // en caso contrario se cambia la variable control y termian el bucle
                control = true;
            }
        }while(!control);
        return aux;
    }
}
