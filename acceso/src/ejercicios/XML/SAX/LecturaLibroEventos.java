/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.SAX;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * clase que utiliza sax para analizar un XML
 * @author linuxdiurno
 */
public class LecturaLibroEventos {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        XMLReader lector;
        try{
            lector = XMLReaderFactory.createXMLReader();
            
            GestorLibrosEventos gestor = new GestorLibrosEventos();
            
            lector.setContentHandler(gestor);
            
            lector.parse("/home/linuxdiurno/prueba/libros.xml");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
