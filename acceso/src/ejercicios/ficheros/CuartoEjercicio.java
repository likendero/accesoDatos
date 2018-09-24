/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author likendero
 */
public class CuartoEjercicio {
    private static Scanner key = new Scanner(System.in);
    private static File directorio = null;
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        GregorianCalendar fecha1 = null;
        GregorianCalendar fecha2 = null;
        try{
            directorio = FicherosTerminal.introDirectorio(key);
            // fecha de inicio 
            System.out.println("fecha inicio de intervalo");
            fecha1 = FicherosTerminal.fechas(key);
            // fecha de fin
            System.out.println("fecha fin de intervalo");
            fecha2 = FicherosTerminal.fechas(key);
            System.out.println("archivos:");
            // creacion del filtro
            FiltroFecha filtro = new FiltroFecha(fecha1, fecha2);
            // se imprimen los ficheros
            System.out.println(FicherosTerminal.directorioFechas(directorio, filtro));
        }catch(NullPointerException nu){
            System.out.println("error en la ejecucion");
            nu.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
}
