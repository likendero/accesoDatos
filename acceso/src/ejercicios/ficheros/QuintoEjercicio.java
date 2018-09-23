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
public class QuintoEjercicio {
    private static File directorio = null;
    private static File directorioDest = null;
    private static Scanner key = new Scanner(System.in);
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        boolean control = true;
        do{
            try{
                // introduccion del usuaro
                // salida
                System.out.println("directorio de primer fichero");
                directorio = FicherosTerminal.introFichero(key);
                // destino
                System.out.println("directorio de fichero destino");
                directorioDest = FicherosTerminal.introFichero(key);
                // comprobacion de que los directorios existen
                if(directorio.exists() && directorioDest.exists()){
                // proceso de lectura
                String txt = FicherosTerminal.lectorTxt(directorio);
                // proceso de escritura en el nuevo directorio
                FicherosTerminal.escritorTxt(directorioDest, txt, true);
                System.out.println("fin del programa");
                }else{
                    System.out.println("uno de los directorios no existe");
                    control = false;
                }
            }catch(NullPointerException nu){
                nu.printStackTrace();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }while(!control);
    }
}
