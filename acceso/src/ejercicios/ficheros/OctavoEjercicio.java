/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author likendero
 */
public class OctavoEjercicio {
    private static File directorio = new File("AleatorioEmple.dat");
    private static Scanner key = new Scanner(System.in);
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        try{
            // introduccion del apellido
            System.out.println("introduce el apellido (max 30 carac)");
            String apellido = cadenas();
            // introduccion del departamento
            System.out.println("introduce el departamento (max 30 carac)");
            String departamento = cadenas();
            // introduccion del salario
            System.out.println("introduce el salario");
            int sala = salario();
            // escritura en el fichero
            escribir(apellido, departamento, sala);
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    /**
     * metodo que escribe la informacion de un empleado al final
     * de un archivo de acceso aleatorio
     * @param apellido
     * @param departamento
     * @param salario 
     */
    private static void escribir(String apellido,String departamento,int salario){
        try{
            int id = 1;
            // creacion del flujo de lectura escritura
            RandomAccessFile escritor = new RandomAccessFile(directorio, "rw");
            // guardado del tamanno original
            long tamano = escritor.length();
            // posicionamiento al final del del documeto
            escritor.seek(escritor.length());
            // se comprueba si el fichero tiene registros
            if(tamano != 0){
                // si tiene se posiciona en el anterior
                escritor.seek(tamano-63);
                // se guarda el id mas 1
                id = escritor.readInt() + 1;
                // se mueve el puntero al final del documento
                escritor.seek(tamano);
                // se escribe el id
                escritor.writeInt(id);
            }else{
                // se escribe el primer id
                escritor.writeInt(id);
            }
            
            
            // annadir apellido
            //escritor.writeUTF(cadenaTreinta(apellido));
            escritor.writeUTF(apellido);
            // escribir departamento
            //escritor.writeUTF(cadenaTreinta(departamento));
            escritor.writeUTF(departamento);
            // escribir salario
            escritor.write(salario);
            escritor.setLength(tamano+63);
            // numero de registros
            System.out.println("el numero de registros es de " + escritor.length() + " " +(escritor.length()/63));
            // cierre de flujo
            escritor.close();
            
        }catch(IOException io){
            System.out.println("error en la escritura");
        }
    }
    /**
     * metodo que permite introducir una cadena
     * @return 
     */
    private static String cadenas(){
        boolean control = false;
        String salida = "";
        do{
            try{
                // introduccion del apellido
                salida = key.next();
                // se comprueba el rango de la cadena
                if(salida.length()>30){
                    // si es demasiado larga se indica y se vuelve a intro
                    System.out.println("es demasiado largo");
                }else{
                    // si esta en rango se termian el bucle
                    control = true;
                }
            }catch(InputMismatchException in){
                System.out.println("error en la introduccion");
                key.nextLine();
            }
        }while(!control);
        // se devuelve la cadena
        return salida;
    }
    /**
     * metodo que permite introducir un numero entero
     * @return 
     */
    private static int salario(){
        int salarioVu = 0;
        boolean control = false;
        do{
            try{
                salarioVu = key.nextInt();
                control = true;
            }catch(InputMismatchException in){
                System.out.println("error en la introduccion");
                key.nextLine();
            }
        }while(!control);
        return salarioVu;
    }
    /**
     * metodo que ajustas las cadenas a 30 elementos
     * @param cadenaOriginal
     * @return 
     */
    private static String cadenaTreinta(String cadenaOriginal){
        // se comrpueba si es necesario ajustar la cadena
        if(cadenaOriginal.length()<30){
            // si lo fuese se annaden los espacios necesarios
            for(int i = 0; i < 30 - cadenaOriginal.length(); i++){
                cadenaOriginal += " ";
            }
        }
        return cadenaOriginal;
    }
}
