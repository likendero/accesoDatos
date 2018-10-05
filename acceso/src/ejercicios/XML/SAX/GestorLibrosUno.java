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
 * @author linuxdiurno
 */
public class GestorLibrosUno extends DefaultHandler {
    private static String etiquetaActual;
    private static boolean bandera = false;
    /**
     * constructor por defecto
     */
    public GestorLibrosUno(){
        super();
    }
    
    @Override
    public void startElement(String url, String nombre, String nombreC, Attributes atrbts) throws SAXException {
        etiquetaActual = nombre;
        // control de que la etiqueta es libro
        if(nombre.equals("libro")){
            // se muestra el ISBN del libro si es impares
            if(Integer.parseInt(atrbts.getValue(0).trim())%2!=0){
                bandera = true;
                System.out.println("ISBN del libro: " + atrbts.getValue(0));
            }
        }     
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        // variable donde se almacena lainformacion
        String texto = new String(chars,i,i1);
        texto = texto.trim();
        
        if(texto.length() > 0 && bandera){
            // selector que hara una accion dependiendo de la etique actual
            switch(etiquetaActual){
                // muestra el titulo
                case "titulo":
                    System.out.println("titulo del libro: " + texto);
                    break;
                    // muestra el numero de paginas
                /*case "paginas":
                    System.out.println("el numero de pagians es " + texto);
                    break;
                    // muestra la editorial
                case "editorial":
                    System.out.println("la editorial es: " + texto);
                    break;
                */
            }
             bandera = false;
        }
        
    }
    
    
    
}
