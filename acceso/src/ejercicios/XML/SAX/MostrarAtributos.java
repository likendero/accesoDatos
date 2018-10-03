/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.SAX;

//import jdk.internal.org.xml.sax.XMLReader;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * lectura de un documento XML a trabes del metodo SAX
 * @author likendero
 */
public class MostrarAtributos {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        XMLReader lector;
        try{
            // instanciacion del lector
            lector = XMLReaderFactory.createXMLReader();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
