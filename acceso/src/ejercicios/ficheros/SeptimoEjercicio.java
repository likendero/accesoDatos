/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author likendero
 */
public class SeptimoEjercicio {
    private static File directorio = null;
    private static Scanner key = new Scanner(System.in);
    
    public static void main(String[] args) {
        try{
            // se pide al usuario el directorio
            directorio = FicherosTerminal.introFicheroEscritura(key);
            // pregunta al usuario y se crea el array de nunmeros
            int[] numeros = numerosAleatorios(cantidad());
            // se comprueba si el directorio existe
            if(directorio.exists()){
                /*
                en el caso que exista ya el fichero annade numeros
                */
                FicherosTerminal.escribirNumeros(directorio, numeros, true);
            }else{
                /*
                en el caso que no exista lo crea y escribe los numeros
                */
                FicherosTerminal.escribirNumeros(directorio, numeros, false);
            }
            System.out.println("los numeros del fichero son");
            System.out.println(FicherosTerminal.leerFicheroNumeros(directorio));
        }catch(NullPointerException nu){
            System.out.println("error nulo");
            nu.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    /**
     * metodo que genera un array de numeros enteros aleatorio
     * @param cantidad numero de numeros aleatorios deseado
     * @return array con los numeros
     */
    public static int[] numerosAleatorios(int cantidad){
        int numeros[] = new int[cantidad];
        // bucle para generar los numeros
        for(int i = 0; i < numeros.length; i++){
            // generacion de numeros aleatorios entre 0 y 100 incluidos
            numeros[i] = (int)(Math.random()*101);
        }
        return numeros;
    }
    /**
     * metodo que pide al usuario el numero de datos a crear
     * @return 
     */
    public static int cantidad(){
        // variable de control para el bucle
        boolean control = false;
        // variable entera que almacena el numero a devolver
        int vuelt = 0;
        do{
            try{
                System.out.println("que cantidad de variables enteras desea crear?");
                // introducion del usuario
                vuelt = key.nextInt();
                // cambio control
                control = true;
            // excepcion que controla si lo introducido es un numero
            }catch(InputMismatchException in){
                System.out.println("se ha introducido un elemento no valido");
                key.nextLine();
            }
        }while(!control);
        return vuelt;
    }
}
