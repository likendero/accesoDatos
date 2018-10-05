/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author likendero
 */
public class GestorSAX extends DefaultHandler {

    public GestorSAX() {
        super();
    }
    
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes atrbts) throws SAXException {
        //super.startElement(string, string1, string2, atrbts); //To change body of generated methods, choose Tools | Templates.
        // sale por pantalla de que etiqueta se trata
        System.out.println("el nombre de la etiqueta es " + nombre);
        // se comprueba si la etiqueta tiene atributos
        if(atrbts.getLength() != 0){
            System.out.println("el nombre de l atributos es "
                    + atrbts.getQName(0)
                    + " su valor es "
                    + atrbts.getValue(0)
            );
            
        }
    }
    
}
