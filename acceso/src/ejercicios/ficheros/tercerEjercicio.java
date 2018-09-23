/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.File;
import java.util.Scanner;

/**
 * programa que borra un directorio
 * @author likendero
 */
public class tercerEjercicio {
    // variable que almacena el directorio de trabajo (el que se borra)
    private static File directorio = null;
    private static Scanner key = new Scanner(System.in);
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        boolean control = false;
        // bucle :3
        do{
        try{
            directorio = FicherosTerminal.introDirectorio(key);
            // se intenta borrar el directorio
            if(!directorio.delete()){
                // se trata de borrar los archivos internos
                FicherosTerminal.recorridoArchivos(directorio);
                if(!directorio.delete()) System.out.println("no se borra");
                else System.out.println("borrado satisfactorio");
            }else{
                System.out.println("borrado satisfactorio");
            } 
            control = true;
            // control null
        }catch(NullPointerException nu){
            System.out.println("error en la ejecucion, puede ser que falten permisos");
            nu.printStackTrace();
            key.nextLine();
            // control de excepciones inesperadas
        }catch(SecurityException se){
            System.out.println("existen archivos imborrables");
            se.printStackTrace();
            // se acaba el bucle ya que la eliminacion es imposible
            control = true;
        }catch(Exception ex){
            System.out.println("error inesperado o en la introduccion");
            key.nextLine();
            control = true;
        }
        }while(!control);
    }
    
}
