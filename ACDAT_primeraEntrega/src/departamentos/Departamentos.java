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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
    
    
}
