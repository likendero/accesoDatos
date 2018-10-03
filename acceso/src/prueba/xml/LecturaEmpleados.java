/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
/**
 * clase de prueba para leer XML
 * @author likendero
 */
public class LecturaEmpleados {
    public static void main(String[] args) {
        File dir = new File("/home/likendero/prueba/empXml.xml");
        
        DocumentBuilder builder;
        Document empleados;
        NodeList elementos;
        
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            empleados = builder.parse(dir);
            empleados.getDocumentElement().normalize();
            elementos = empleados.getElementsByTagName("Empleado");
            System.out.println(elementos.getLength());
            for(int i = 0; i < elementos.getLength(); i++){
                // se saca el elemento actual
                Node nodoAc = elementos.item(i);
                
                if(nodoAc.getNodeType() == Node.ELEMENT_NODE){
                    Element elem = (Element) nodoAc;
                    System.out.println(leer("id", elem));
                    System.out.println(leer("apellido", elem));
                    System.out.println(leer("departamento", elem));
                    System.out.println(leer("sueldo", elem));
                            
                    System.out.println();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private static String leer(String tag,Element elem){
        NodeList nodL = elem.getElementsByTagName(tag).item(0).getChildNodes();
        Node valor = (Node)nodL.item(0);
        return valor.getNodeValue();
    
    }
}
