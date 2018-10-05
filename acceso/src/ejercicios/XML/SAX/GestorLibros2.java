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
public class GestorLibros2 extends DefaultHandler {
    private static String etiquetaActual;
    private static String ISBN;
    private static String titulo;
    private static boolean bandera = false;
    /**
     * constructor por defecto
     */
    public GestorLibros2(){
        super();
    }
    
    @Override
    public void startElement(String url, String nombre, String nombreC, Attributes atrbts) throws SAXException {
        // captura etiqueta actual
        etiquetaActual = nombre;
        // captura del ISBN
        if(nombre.equals("libro")){
            ISBN = atrbts.getValue(0);// utilizar el nombre del atributo
        }
        
        }     
    

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        // variable donde se almacena lainformacion
        String texto = new String(chars,i,i1);
        texto = texto.trim();
        
        if(texto.length() > 0 ){
            // selector que hara una accion dependiendo de la etique actual
            switch(etiquetaActual){
                // muestra el titulo
                case "titulo":
                        titulo = texto;
                    break;
                    // muestra el numero de paginas
                case "paginas":
                    if(Integer.parseInt(texto) > 150){
                        System.out.println("el ISBN es: " + ISBN);
                        System.out.println("titulo del libro: " + titulo);
                        System.out.println("el numero de pagians es " + texto);
                        bandera = true;
                    }
                    bandera = false;
                    break;
                    // muestra la editorial
                case "editorial":
                    if(bandera)
                    System.out.println("la editorial es: " + texto);
                    break;
                
            }
             
        }
    }    
}