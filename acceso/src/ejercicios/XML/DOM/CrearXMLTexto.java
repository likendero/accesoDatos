
package ejercicios.XML.DOM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *  se crea un XML a partir de un fichero de Texto con numeros
 * @author likendero
 */
public class CrearXMLTexto {
    private static File directorio = null;
    private static Scanner key = new Scanner(System.in);
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        int numero;
        
        Source source;
        Result result;
        try{
            System.out.println("directorio donde realizar las operaciones");
            directorio = FicherosTerminal.introDirectorio(key);
            CrearFichero.CrearFicheroTexto(directorio);
            // creacion del flujo de lectura
            FileReader flujo = new FileReader(new File(directorio,"numeros.txt"));
            BufferedReader lector = new BufferedReader(flujo);
            // creacion factory
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            // creacion del builder 
            DocumentBuilder builder = fac.newDocumentBuilder();
            DOMImplementation imple = builder.getDOMImplementation();
            //Creacion del document
            Document doc = imple.createDocument(null, "numeros", null);
            doc.setXmlVersion("1.0");
            // variable para la lectura
            String aux = lector.readLine();
            
            while(aux != null){
                
                Element elem = doc.createElement("numero");
                // creo elemento
                Text text = doc.createTextNode(aux);
                // pegar nuevo elemento a la raiz actual
                doc.getDocumentElement().appendChild(elem);
                // pegamos el valor
                elem.appendChild(text);
                
                aux = lector.readLine();
            }
            lector.close();
            flujo.close();
            // creacion del fichero
            source = new DOMSource(doc);
            result = new StreamResult(new File(directorio,"numeros.xml"));
            
            // objeto transformador
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }catch(Exception ex){
            System.out.println("error en la ejecucion");
            ex.printStackTrace();
            
        }
    }
}
