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
public class GestorLibrosEventos extends DefaultHandler {
    private String etiquetaActual,ISBN,titulo,paginas,editorial;
    @Override
    public void startElement(String ur, String nombre, String nombreC, Attributes atrbts) throws SAXException {
        
        // captura del isbn
        if(nombre.equals("libro")){
            ISBN = atrbts.getValue("ISBN");
        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        String texto = new String(chars,i,i1).trim();
        
        // diversificacion de casos
        switch(etiquetaActual){
            // caso del titulo
            case "titulo":
                titulo = texto;
                break;
            // caso del numero de paginas
            case "paginas":
                paginas = texto;
                break;
            // caso para la editorial
            case "editorial":
                editorial = texto;
                break;
        }
        
    }

    @Override
    public void endElement(String uri, String name, String nameq) throws SAXException {
        if(name.equals("libro")){
            // comprobacion del numero de paginas
            if(Integer.parseInt(paginas) > 150){
                // se muestra el ISBN
                System.out.println("el ISBN del libro es: " + ISBN);
                // se muestra el libro
                System.out.println("el titulo del libro es: " + titulo);
                // el numero de pagianas
                System.out.println("el numero de paginas del libro es: " + paginas);
                // la editorial es
                System.out.println("el titulo del libro es: " + editorial);
            }
            
        }
        etiquetaActual = "";
    }
    
    
    
}
