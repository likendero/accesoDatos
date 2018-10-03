/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.DOM;

import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 *
 * @author likendero
 */
public class PruebaLeerBinarios {
    public static void main(String[] args) {
        try{
            FileInputStream flujo = new FileInputStream("/home/likendero/prueba/numerosdat.dat");
            DataInputStream lector = new DataInputStream(flujo);
            // lectura
            while(true)
                System.out.println(lector.readInt());
        }catch(Exception ex){
            System.out.println("fin");
        }
    }
}
