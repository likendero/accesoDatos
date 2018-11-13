/*
 * Copyright (C) 2018 likendero
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package departamentos;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author likendero
 */
public class Departamentos implements Serializable{
    private String id;
    private String tipo;
    private String nombre;
    private String domicilio;
    private String ciudad;
    private String codigoPostal;
    private String provincia;
    private String pais;
    private static final long serialVersionUID = 1L;
    /**
     * constructor de departamentos
     */
    public Departamentos(){
        super();
        // creacion de los datos a null
        id = null;
        tipo = null;
        nombre = null;
        domicilio = null;
        ciudad = null;
        codigoPostal = null;
        provincia = null;
        pais = null;
    }
    /**
     * constructor con todos los parametros de la clase
     * @param id
     * @param tipo
     * @param nombre
     * @param domicilio
     * @param ciudad
     * @param codigoPostal
     * @param provincia
     * @param pais 
     */
    public Departamentos(String id, String tipo, String nombre, String domicilio, String ciudad, String codigoPostal, String provincia, String pais) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.pais = pais;
    }
    
    
    // GETTERS SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "departamentos{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", domicilio=" + domicilio + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", provincia=" + provincia + ", pais=" + pais + '}';
    }
    
    /////////////////METODOS//////////////////
    /**
     * metodo que guarda la informacion del objeto en 
     * un fichero
     * @param directorio ruta de a la carpeta donde se desea guardar
     * @param nombreFichero nombre del fichero que se quiereguardar sin extension
     */
    public void saveData(String nombreFichero,String directorio)throws IOException{
        File dir = new File(directorio);
        File fichero = new File(directorio,nombreFichero + ".dat");
        // comprobacion que el directorio existe
        if(dir.exists()){
            // creacion del flujo
            FileOutputStream flujo = new FileOutputStream(fichero);
            // formateo de la salida
            ObjectOutputStream escritor = new ObjectOutputStream(flujo);
            // escritura
            escritor.writeObject(this);
            // cerrado de escritor
            escritor.close();
            // cerrado del flujo
            flujo.close();
        }else{
            // caso que la carpeta introducida no exista
            throw new IOException("la carpeta no existe");
        }
    }
    // guardar XML
    /**
     * 
     * @param nombre
     * @param dir 
     * @throws java.io.FileNotFoundException 
     */
    public void saveXMLXstream(String nombre, String dir) throws FileNotFoundException, IOException{
        // para poder usar este metodo es necesario importar el jar
        // creacion de un Xstream nuevo para la creacion del xml
        XStream xtream = new XStream();
        
        // indicar la clase que se ca usar para combertir
        xtream.alias("departamento", Departamentos.class);
        /*
            en este punto le pasamos la instancia del objeto que queremos combertir
            el dato que nos devuelve es de tipo cadena la cual nos servira para escribir 
            mas tarde el documento en un fichero, para escribir directamente debemos 
            pasarle tambien un flujo de salida
        */
        // creacion de un flujo de salida
        FileOutputStream flujo = new FileOutputStream(new File(dir,nombre + ".xml"));
        //  escritura del xml
        xtream.toXML(this, flujo);
        flujo.close();
    }
    /**
     * metodo que a trabes de DOM guarda la informacion 
     * en un fichero XML
     * 
     * @param nombreFichero
     * @param dir
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws javax.xml.transform.TransformerConfigurationException
     * @throws java.io.FileNotFoundException
     */
    public void saveXML(String nombreFichero, String dir) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, FileNotFoundException{
        // factory para crear el parser
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // creacion del del parser
        DocumentBuilder builder = factory.newDocumentBuilder();
        // creacion de la implementacion
        DOMImplementation implementacion = builder.getDOMImplementation();
        // creacion del documento
        Document document = implementacion.createDocument(null,"departamentos" , null);
        // cambio version XML
        document.setXmlVersion("1.0");
        // captura del elemento raiz del documento
        Element raiz = document.getDocumentElement();
        
        // creacion de los sub elementos
        Element idEl = document.createElement("id");
        Element tipoEl = document.createElement("tipo");
        Element nombreEl = document.createElement("nombre");
        Element domicilioEl = document.createElement("domicilio");
        Element ciudadEl = document.createElement("ciudad");
        Element cpEl = document.createElement("cp");
        Element provinciaEl = document.createElement("provincia");
        Element paisEl = document.createElement("pais");
        
        // creacion del texto
        Text idTx = document.createTextNode(this.id);
        Text tipoTx = document.createTextNode(this.tipo);
        Text nombreTx = document.createTextNode(this.nombre);
        Text domicilioTx = document.createTextNode(this.domicilio);
        Text ciudadTx = document.createTextNode(this.ciudad);
        Text cpTx = document.createTextNode(this.codigoPostal);
        Text provinciaTx = document.createTextNode(this.provincia);
        Text paisTx = document.createTextNode(this.pais);
        
        // annadir a la estructura
        raiz.appendChild(idEl);
        raiz.appendChild(tipoEl);
        raiz.appendChild(nombreEl);
        raiz.appendChild(domicilioEl);
        raiz.appendChild(ciudadEl);
        raiz.appendChild(cpEl);
        raiz.appendChild(provinciaEl);
        raiz.appendChild(paisEl);
        // annadir textos
        idEl.appendChild(idTx);
        tipoEl.appendChild(tipoTx);
        nombreEl.appendChild(nombreTx);
        domicilioEl.appendChild(domicilioTx);
        ciudadEl.appendChild(ciudadTx);
        cpEl.appendChild(cpTx);
        provinciaEl.appendChild(provinciaTx);
        paisEl.appendChild(paisTx);
        
        // escritura del documento
        Source source = new DOMSource(document);
        //FileOutputStream out = new FileOutputStream(new File(dir,nombreFichero+".xml"));
        Result result = new StreamResult(new File(dir,nombreFichero + ".xml"));
        // creacion del transformer
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        // transformacion
        trans.transform(source, result);
    }
    
    ////////////////ESTATICOS/////////////////////////////////////
   
    /**
     * metodo que muestra por pantalla la informacion dentro de un fichero
     * @param dir directorio del fichero a leer
     * @return un objeto del tipo fichero
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static Departamentos leerFichero(File dir) throws FileNotFoundException, IOException, ClassNotFoundException{
        // apertura del flujo 
        FileInputStream flujo =  new FileInputStream(dir);
        // creacion del lector
        ObjectInputStream lector = new ObjectInputStream(flujo);
        
        // creacion de un departamento nuevo
        Departamentos dep = (Departamentos)lector.readObject();
        // retorno del departamento
        return dep;
    }
    
    
    
    /////////////// Lectura y Escritura de varios objetos a la vez //////////////////////////
    /**
     * para guardar varios departamentos
     * @param list lista de departamentos que se van a guardar
     * se usa una lista al tratarse de un objeto siendo facilmente guardor varios objetos a la 
     * vex
     * @throws java.io.FileNotFoundException
     */
    public static void saveDataList(ArrayList<Departamentos> list,File dir,String nombre) throws FileNotFoundException, IOException{
        FileOutputStream flujo = new FileOutputStream(new File(dir,nombre+".dat"));
        ObjectOutputStream escritor = new ObjectOutputStream(flujo);
        escritor.writeObject(list);
    }
    /**
     * 
     * @param dir
     */
    public static ArrayList<Departamentos> readDataList(File dir) throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Departamentos> dep = null;
        FileInputStream flujo = new FileInputStream(dir);
        ObjectInputStream lector = new ObjectInputStream(flujo);
        dep = (ArrayList<Departamentos>) lector.readObject();
        return dep;
    } 
     /**
     * metodo para guardar un listado de departamentos en forma de xml
     * a trabes del metodo dom
     * @param deps
     * @param dir
     * @param nombreFichero
     */
    public static void saveXMLList(ArrayList<Departamentos> deps, File dir, String nombreFichero) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        // creacion del factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // creacion de un builder/parser
        DocumentBuilder builder = factory.newDocumentBuilder();
        // creacion de un domIMplementation
        DOMImplementation impl = builder.getDOMImplementation();
        // Creacion del documento
        Document document = impl.createDocument(null, "departamentos", null);
        document.setXmlVersion("1.0");
        Element raiz = document.getDocumentElement();
        Element aux;
        for(Departamentos depAux: deps){
          // creacion de un elemento departamento nuevo
          aux = document.createElement("departamento");
          // enlace del departamento con el elemento raiz
          raiz.appendChild(aux);
          // creacion de los sub elementos

            Element idEl = document.createElement("id");
            Element tipoEl = document.createElement("tipo");
            Element nombreEl = document.createElement("nombre");
            Element domicilioEl = document.createElement("domicilio");
            Element ciudadEl = document.createElement("ciudad");
            Element cpEl = document.createElement("cp");
            Element provinciaEl = document.createElement("provincia");
            Element paisEl = document.createElement("pais");

            // creacion del texto
            Text idTx = document.createTextNode(depAux.getId());
            Text tipoTx = document.createTextNode(depAux.getTipo());
            Text nombreTx = document.createTextNode(depAux.getNombre());
            Text domicilioTx = document.createTextNode(depAux.getDomicilio());
            Text ciudadTx = document.createTextNode(depAux.getCiudad());
            Text cpTx = document.createTextNode(depAux.getCodigoPostal());
            Text provinciaTx = document.createTextNode(depAux.getProvincia());
            Text paisTx = document.createTextNode(depAux.getPais());

            // annadir a la estructura
            aux.appendChild(idEl);
            aux.appendChild(tipoEl);
            aux.appendChild(nombreEl);
            aux.appendChild(domicilioEl);
            aux.appendChild(ciudadEl);
            aux.appendChild(cpEl);
            aux.appendChild(provinciaEl);
            aux.appendChild(paisEl);
            // annadir textos
            idEl.appendChild(idTx);
            tipoEl.appendChild(tipoTx);
            nombreEl.appendChild(nombreTx);
            domicilioEl.appendChild(domicilioTx);
            ciudadEl.appendChild(ciudadTx);
            cpEl.appendChild(cpTx);
            provinciaEl.appendChild(provinciaTx);
            paisEl.appendChild(paisTx);
        }
        // escritura del documento
        Source source = new DOMSource(document);
        //FileOutputStream out = new FileOutputStream(new File(dir,nombreFichero+".xml"));
        Result result = new StreamResult(new File(dir,nombreFichero + ".xml"));
        // creacion del transformer
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        // transformacion
         trans.transform(source, result);
    }
}
