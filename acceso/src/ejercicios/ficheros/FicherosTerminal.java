/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.GregorianCalendar;
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
     * metodo que sirve para introducir el directorio de un fichero
     * @param key
     * @return
     * @throws Exception 
     */
    public static File introFichero(Scanner key)throws Exception{
        /*
        variables:
        aux objeto File que comprueba si el directorio es correcto
        control que gestiona el bucle
        */
        File aux = null;
        boolean control = false;
        // inicio del bucle
        do{
            System.out.println("introduzca el directiro de un fichero: ");
            // sen introduce por terminale el directorio en la variable File
            aux = new File(key.next());
            // se comprueba si es un fichero valido

            if(!aux.isFile()){
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
     * metodo que devuelve un directorio de un fichero que puede ser creado
     * @param key
     * @return
     * @throws Exception 
     */
    public static File introFicheroEscritura(Scanner key)throws Exception{
        /*
        variables:
        aux objeto File que comprueba si el directorio es correcto
        control que gestiona el bucle
        */
        File aux = null;
        boolean control = false;
        // inicio del bucle
        do{
            System.out.println("introduzca el directiro de un fichero: ");
            // sen introduce por terminale el directorio en la variable File
            aux = new File(key.next());
            // se comprueba si existe el fichero
            if(!aux.exists()){
                // si no existe se le pregunta al usuario si desea crearlo
                System.out.println("el archivo no existe, desea crearlo?(s/n)");
                char opcion = ' ';
                do{
                    opcion = key.next().charAt(0);
                    switch(opcion){
                        case 's':
                        case 'S':
                            return aux;
                        case 'n':
                        case 'N':
                            break;
                        default:
                            System.out.println("respuesta no valida");
                            break;
                    };
                }while((opcion != 's' || opcion != 'S' ) && 
                        (opcion != 'n' || opcion != 'N') 
                        );
            }else{
                if(!aux.isFile()){
                    // en el caso que no sea asi se manda un mensaje por pantalla 
                    // y el bucle continua
                    System.out.println("no es un directorio valido");
                }
                else{
                    // en caso contrario se cambia la variable control y termian el bucle
                    control = true;
                }
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
    /**
     * metodo que lee la informacion de un fichero
     * @param dir directorio a leer
     * @return cadena con todo el documento
     */
    public static String lectorTxt(File dir){
        String salida = "";
        String aux = "";
        try{
            // creacion del flijo de lectura
            FileReader lector = new FileReader(dir);
            // objeto para el formateo de la entrada
            BufferedReader flujoLec = new BufferedReader(lector);
            // primera lectura del documeto
            aux = flujoLec.readLine();
            // la lectura acaba cuando salte una ioexception
            while(aux != null){
                // seguarda en la variable final
                salida += "\n" + aux;
                // se realiza otra lectura 
                aux = flujoLec.readLine();
            }
        }catch(IOException io){
            System.out.println("error en la lectura");
            io.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
        // salida del metodo
        return salida;
    }
    /**
     * metodo que escribe una cadena de texto en un fichero           
     * @param dir directorio del fichero
     * @param txt cadena de texto
     * @param sobreEscritua indica si se quiere sobre escribir
     * o escribir a continuacion
     */
    public static void escritorTxt(File dir,String txt,boolean sobreEscritua){
        try{
            FileWriter escritor = new FileWriter(dir,sobreEscritua);
            BufferedWriter flujoEscr = new BufferedWriter(escritor);
            flujoEscr.write(txt);
            System.out.println("fin esxcritura");
            // cerrado de flujo
            flujoEscr.close();
            escritor.close();
        }catch(IOException io){
            System.out.println("error en la escritura");
            io.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    /**
     * metodo que convierte las letras en minuscula a mayusculas
     * y viceversa
     * @param entrada cadena de texto que se quiere
     * @return 
     */
    public static String invertir(String entrada){
        // combierto el string en un array de caracteres
        char caracteres[] = entrada.toCharArray();
        String salida = "";
        // recorrido del array de caracteres
        for(int i = 0; i < caracteres.length;i++){
            // comprobacion de si es una letra
            if(Character.isLetter(caracteres[i])){
                // comprobacion de minuscula o mayuscula
                if(Character.isLowerCase(caracteres[i])){
                    // se combierte en mayuscula al mismo tiempo que se annade
                    // a la cadena
                    salida += Character.toUpperCase(caracteres[i]);
                }else{
                    // se suma en minuscula
                    salida += Character.toLowerCase(caracteres[i]);
                }
            }else{
                // en caso de no ser letra el elemento se annade tal cual
                salida += caracteres[i];
            }
        }
        return salida;
    }
    public static void escribirNumeros(File dir,int[] numeros,boolean bandera){
        try{
            // creacion del flujo de datos
            FileOutputStream salida = new FileOutputStream(dir,bandera);
            // creacion del formateo de datos
            DataOutputStream escritor = new DataOutputStream(salida);
            // recorrido del array de enteros
            for(int i = 0; i < numeros.length; i++){
                // escritura de los numeros
                escritor.writeInt(numeros[i]);
            }
            escritor.close();
            salida.close();
        }catch(IOException io){
            System.out.println("error en la escritura");
            io.printStackTrace();
        }
    }
    public static String leerFicheroNumeros(File dir){
        // cadena que se devolvera
        String salida = "";
        try{
            // creacion del flujo de datos
            FileInputStream flujo = new FileInputStream(dir);
            // creacion del lector con formato
            DataInputStream lector = new DataInputStream(flujo);
            while(true){
                salida += "\n" + lector.readInt();
            }
        }catch(IOException io){
            System.out.println("fin lectura");
            //io.printStackTrace();
        }
        return salida;
    }
    /**
     * metodo para filtrar archivos segun un intervalo de fechas
     * @param dir
     * @param filtro
     * @return 
     */
    public static String directorioFechas(File dir,FiltroFecha filtro){
       // variable para devolver la cadena
       String salida = "";
       
       for(File i: dir.listFiles((FileFilter) filtro)){
           salida += i.getName() + "\n";  
       }
       return salida;
    }
    /**
     * metodo para preguntar al usuario por una fecha
     * @param key
     * @return 
     */
    public static GregorianCalendar fechas(Scanner key){
        int dia = 0;
        int mes = 0;
        int anno = 0;
        boolean control = false;
        // intro del dia
        do{
            try{
                // introduccion de los parametros
                System.out.println("introduce el dia");
                dia = key.nextInt();
                System.out.println("introduce mes");
                mes = key.nextInt();
                System.out.println("introduce año");
                anno = key.nextInt();
                // comprobacion de la validez de la fecha
                LocalDate.of(anno,mes,dia);
                control = true;
            }catch(DateTimeException da){
                System.out.println("la fecha no es correcta");
            }
        }while(!control);
            
        return new GregorianCalendar(anno, mes, dia);
    }
}
