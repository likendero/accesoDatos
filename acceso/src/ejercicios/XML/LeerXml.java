/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import  org.w3c.dom.*;
import org.xml.sax.SAXException;
/**
 * clase que lee un fichero xml y guarda la informacion en 
 * un fichero binario
 * @author likendero
 */
public class LeerXml {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args){
        File dir = new File("/home/likendero/prueba/numeros.xml");
        // creacion de variables para parseo
        Document document;
        DocumentBuilder builder;
        NodeList numeros;
        Node numero;
        // creacion de factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        ArrayList<Integer> numerosAux = new ArrayList<Integer>();
        
        try{
            // creacion del parser
            builder = factory.newDocumentBuilder();
            document = builder.parse(dir);
            // optencion de los elementos
            document.getDocumentElement().normalize();
            // creacion de lista de nodos
            numeros = document.getElementsByTagName("numero");
            // recorrido de los elementos
            System.out.println(numeros.getLength());
            for(int i = 0; i < numeros.getLength(); i++){
                // se captura el elemento
                numero = numeros.item(i);
                
                // se saca un elemento a partir del nodo
                Element elem = (Element) numero;
                if(elem.getNodeType() == Node.ELEMENT_NODE){
                    // se saca un nodelist para leer los elementos
                    NodeList nodo = elem.getElementsByTagName("numero").item(0).getChildNodes();
                    // sacar un nodo final para despues sacar su valor
                    Node valorNodo = (Node)nodo.item(0);
                    System.out.println(Integer.parseInt(valorNodo.getNodeValue()));
                    numerosAux.add(Integer.parseInt(valorNodo.getNodeValue()));
                }
                
            }
        }catch(Exception ex){
        
            ex.printStackTrace();
        }
        escribirNumeros(numerosAux);
    }
    private static void escribirNumeros(ArrayList<Integer> numeros){
        try{
            FileOutputStream flujo = new FileOutputStream("/home/likendero/prueba/numerosdat.dat");
            DataOutputStream escritor = new DataOutputStream(flujo);
            // recorrido de escritura
            for(Integer i : numeros){
                escritor.writeInt(i.intValue());
            }
            // cierre de flujo
            escritor.close();
            flujo.close();
        }catch(IOException io){
            io.printStackTrace();
            System.out.println("error de escritura");
        }
    } 
}
