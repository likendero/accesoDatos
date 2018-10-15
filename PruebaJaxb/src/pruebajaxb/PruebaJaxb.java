/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajaxb;


import java.io.File;
import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//import pruebaJaxb.Articulos;
import pruebaJaxb.Direccion;
//import pruebaJaxb.ObjectFactory;
import pruebaJaxb.PedidoType;


/**
 *
 * @author likendero
 */
public class PruebaJaxb {
    private static File directorio = new File( "/home/likendero/github/acesoDatos/filez/Scripts/albaran.xml");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            JAXBContext jAXBContext = JAXBContext.newInstance("pruebaJaxb");
            Unmarshaller u = jAXBContext.createUnmarshaller();
            JAXBElement element = (JAXBElement) u.unmarshal(directorio);
            PedidoType pedi =  (PedidoType) element.getValue();
            Direccion dir = pedi.getFacturarA();
            
            dir.setNombre("Javier Gonzalez");
            dir.setCalle("gibraltar español");
            dir.setCiudad("Almeria");
            dir.setProvincia("Almeria");
            dir.setCodigoPostal(new BigDecimal("30500"));
            
            Marshaller m = jAXBContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(element,directorio );
        }catch(Exception ex){
           
        }
        
    }
    
}
