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
public class CrearXml {
    
    public static void main(String[] args) {
        // directorios
        File ficheroIn = new File("/home/likendero/prueba/Empleados.dat");
        File ficheroOut = new File("/home/likendero/prueba/empXml.xml");
        // cosas del XML
        Source source;
        Result result;
        // datos
        int id,salario, posicion;
        String apellido = new String(), departamento;
        // necesario para parsear
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            // Creacion de flujo de lectura
            RandomAccessFile file = new RandomAccessFile(ficheroIn, "r");
            
            // objeto procsador o parser
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // implementacion para Dom
            DOMImplementation implementation = builder.getDOMImplementation();
            
            // creacion del dcumento
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");
            // posicion para comienzo de lectura
            posicion = 0;
            
            while(posicion < file.length()){
                // nos posicionamos
                file.seek(posicion);
                // obtener id empleado
                id = file.readInt();
                // lectura del apellido
                apellido = file.readUTF();
                // lectura de departamento
                departamento = file.readUTF();
                // lectura departamente
                salario = file.readInt();
                
                if(id > 0){
                    // creacion de un elemento que hace de raiz
                    Element raiz = document.createElement("Empleado");
                    // posicionamiento de la raiz
                    document.getDocumentElement().appendChild(raiz);
                    // añadimos sub elementos y sus valores
                    // creacion del elemento id
                    CrearElemento("id", Integer.toString(id), raiz, document);
                    // creacion del elemento apellido
                    CrearElemento("apellido", apellido, raiz, document);
                    // creacion del elemento departamento
                    CrearElemento("departamento", departamento, raiz, document);
                    // creacion del elemto Salario
                    CrearElemento("salario", Integer.toString(salario), raiz, document);
                }
                // cambio de la posicion
                posicion = posicion + 122;
            }
            
            // una vez creada la estructura
            // creacion del fichero de salida
            source = new DOMSource(document);
            result = new StreamResult(ficheroOut);
            
            // objeto para realizar la transformacion
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            file.close();
        }catch(IOException io){
            System.out.println("error de lectura escritura");
            io.printStackTrace();
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    /**
     * metodo que introduce los valores de los elementos hoja
     * @param dato
     * @param valor
     * @param raiz
     * @param document 
     */
    private static void CrearElemento(String dato,String valor,Element raiz, Document document){
        // cracion del hijo
        Element elem = document.createElement(dato);
        // damos valor
        Text text = document.createTextNode(valor);
        // pegamos el element hijo a la raiz
        raiz.appendChild(elem);
        // pegamos el valor
        elem.appendChild(text);
    }
    
}
