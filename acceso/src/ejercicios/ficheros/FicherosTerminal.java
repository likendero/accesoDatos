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
    /**
     * metodo que de forma recursiva recorre los arboles de archibos hasta borrarlos
     * completamente
     * @param dir 
     */
    public static void recorridoArchivos(File dir)throws SecurityException{
        File aux = null;
        // recorrido de los ficheros del directorio
        for(String i: dir.list()){
            // se crea el objeto file para trabajar con los elementos
            aux = new File(dir.getAbsolutePath(),i);
            // en el caso que sea un directorio
            if(aux.isDirectory()){
                // se trata de eliminar
                if(!aux.delete()){
                    /*
                    si no fuese posible se accederia para tratar de eliminar
                    todos los elementos que tubiese dentro
                    */
                    recorridoArchivos(aux);
                    if(!aux.delete()) throw new SecurityException();
                }
                // si fuese un fichero se trata de eliminar
            }else if(!aux.delete()){
                // si no se puede se lanza una excepcion ya que no se puede borrar
                throw new SecurityException();
            }
        }
    }
}
