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
public class LeerXML {
    public static void main(String[] args) {
        File fileIn = new File("/home/likendero/prueba/numeros.xml");
        
        Document doc;
        DocumentBuilder builder;
        NodeList Empleados;
        Node emple;
        
        // para la creacion del parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    }
}
