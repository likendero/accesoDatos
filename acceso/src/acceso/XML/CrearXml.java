/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso.XML;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
/**
 *
 * @author likendero
 */
public class CrearXml {
    
    public static void main(String[] args) {
        // directorios
        File ficheroIn = new File("/home/likendero/prueba/Empleados.dat");
        File ficheroOut = new File("/home/likendero/prueba/empXml.xml");
        // cosas del XML
        Source source;
        Result result;
        // datos
        int id,salario;
        String apellido, aux;
        // creacion flujo fichero aleatorio
        try{
            RandomAccessFile file = new RandomAccessFile(ficheroIn, "r");
        
        }catch(IOException io){
            System.out.println("error de lectura escritura");
            io.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
}
