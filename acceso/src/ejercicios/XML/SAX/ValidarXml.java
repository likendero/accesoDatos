
package ejercicios.XML.SAX;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.*;

/**
 *  clase que sirve para ver si el documento xml esta bien formado
 * @author linuxdiurno
 */
public class ValidarXml {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        try{
            // creacion del generador
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // se desactiva la validacion puesto que 
            //no se tiene documento XSD ni DTD
            factory.setValidating(false);
            // se genera el parser segun lo definido
            SAXParser parser = factory.newSAXParser();
            // se genera el lector segun el Parser
            XMLReader reader = parser.getXMLReader();
            // se define el generador de errores
            reader.setErrorHandler(new GestorHerrores());
            // se trata de parsear el docuemnto
            reader.parse("/home/linuxdiurno/prueba/librosConErrores.xml");
        }catch(ParserConfigurationException par){
            System.out.println("error en el parser");
            par.printStackTrace();
        }catch(SAXException sa){
            System.out.println(sa.getMessage());
            //sa.printStackTrace();
        }catch(IOException io){
            System.out.println("Error entrada salida");
            io.printStackTrace();
        }
    }
}
