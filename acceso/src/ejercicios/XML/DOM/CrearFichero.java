
package ejercicios.XML.DOM;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
/**
 *
 * @author likendero
 */
public class CrearFichero {
    
    /**
     * metodo que crear un fichero de texto con 
     * 10 numeros aleatorios en un directorio indicado
     */
    public static void CrearFicheroTexto(File dir){
        dir = new File(dir,"numeros.txt");
        try{
            FileWriter flujo = new FileWriter(dir);
            BufferedWriter escritor = new BufferedWriter(flujo);
            // array de numeros con todo los numeros que se van a escribir
            int numeros[] = new int[10];
            // bucle que annade los numeros
            for(int i = 0 ; i  < numeros.length ; i++){
                // numeros de entre 0 a 100 generados aleatoriamente
                numeros[i] = (int)(Math.random()*101);
            }
            // crear el fichero con los numeros
            for(int i = 0 ; i < numeros.length; i++){
                // es escribe el numero
                escritor.write(Integer.toString(numeros[i]));
                // salto de linea
                escritor.newLine();
            }
            escritor.close();
            flujo.close();
        }catch(IOException io){
            System.out.println("error en la creacion del fichero");
        }
    }
}
