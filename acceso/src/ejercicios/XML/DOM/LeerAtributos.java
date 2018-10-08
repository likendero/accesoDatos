/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.DOM;


import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 *
 * @author likendero
 */
public class LeerAtributos {
    public static void main(String[] args) {
        try{
            // factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // parser
            DocumentBuilder parser = factory.newDocumentBuilder();
            // DOM
            Document documento = parser.parse(new File("/home/likendero/prueba/libros.xml"));
            documento.getDocumentElement().normalize();
            NodeList nodos = documento.getElementsByTagName("libro");
            for(int i = 0; i < nodos.getLength(); i++){
                // captura del nodo
                Node libro = nodos.item(i);
                
                NamedNodeMap atributos = libro.getAttributes();
                Node atributo = atributos.getNamedItem("ISBN");
                System.out.println("ISBN del libro es: " + atributo.getNodeValue());
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
