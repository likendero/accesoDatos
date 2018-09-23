/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.File;
import java.util.Scanner;
/**
 *
 * @author likendero
 */
public class SextoEjercicio {
    private static File directorio = null;
    private static Scanner key = new Scanner(System.in);
    /**
     * metodo priuncipal
     * @param args 
     */
    public static void main(String[] args) {
        try{
            // introduccion del directorio
            directorio = FicherosTerminal.introFicheroEscritura(key);
            System.out.println("introduzca la frase que desea introducir");
            // guarado de un texto desde teclado
            key.nextLine();
            String frase = key.nextLine();
            // escritura del fichero
            FicherosTerminal.escritorTxt(directorio, frase, false);
            // salida por pantalla invertido del fichero
            System.out.println(
                    FicherosTerminal.invertir(
                            FicherosTerminal.lectorTxt(directorio)
                    )
            );
        }catch(NullPointerException nu){
            System.out.println("error");
            nu.printStackTrace();
        }catch(Exception ex){
            System.out.println("error en el programa");
            ex.printStackTrace();
        }
        
    }
    
}
