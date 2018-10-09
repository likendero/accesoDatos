/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.XML.SAX;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author linuxdiurno
 */
public class GestorHerrores implements ErrorHandler{

    @Override
    public void warning(SAXParseException saxpe) throws SAXException {
        throw new SAXException("podria haber fallos");
       
    }

    @Override
    public void error(SAXParseException saxpe) throws SAXException {
        System.out.println("el fichero no esta formado segun el esquema");
    }

    @Override
    public void fatalError(SAXParseException saxpe) throws SAXException {
        throw new SAXException("error fatal fin del porgrama");
    }
    
}
