/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author likendero
 */
public class NovenoEjercicio {
    private static File directorio = new File("AleatorioEmple.dat");
    private static Scanner key = new Scanner(System.in);
    private static final int TAMANNO = 63;
    
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        try{
            eliminar(OctavoEjercicoLectura.seleccion());
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    /**
     * 
     * @param registro 
     */
    private static void eliminar(int registro){
        try{
            registro -= 1;
            // creacion de flujo
            RandomAccessFile escritor = new RandomAccessFile(directorio, "rw");
            // se posiciona en el registro
            escritor.seek(TAMANNO*registro);
            // se salta el id
            escritor.readInt();
            // se guarda la informacion del registro
            String apellido = escritor.readUTF();
            String departamento = escritor.readUTF();
            int sueldo = escritor.readInt();
            // se reescribe con el id -1
            escritor.seek(TAMANNO*registro);
            escritor.writeInt(-1);
            escritor.writeUTF(apellido);
            escritor.writeUTF(departamento);
            escritor.writeInt(sueldo);
            // se cierra el flujo
            escritor.close();
        }catch(IOException io){
            System.out.println("error de escritura");
            io.printStackTrace();
        }
    }
}
