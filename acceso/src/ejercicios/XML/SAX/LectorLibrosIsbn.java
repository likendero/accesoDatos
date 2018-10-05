/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.SAX;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author linuxdiurno
 */
public class LectorLibrosIsbn {
    /**
     * metodo principal donde se hara la lectura
     * @param args 
     */
    public static void main(String[] args) {
        XMLReader lector;
        try{
            // creacion del parser lector
            lector = XMLReaderFactory.createXMLReader();
            // creacion del gestor (Handler)
            GestorLibrosUno gestor = new GestorLibrosUno();
            // definir handler
            lector.setContentHandler(gestor);
            //parseado
            lector.parse("/home/linuxdiurno/prueba/libros.xml");
        }catch(SAXException sax){
            System.out.println("error SAX");
            sax.printStackTrace();
        }catch(IOException io){
            System.out.println("error en la lectura");
            io.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
}
