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

/**
 *
 * @author likendero
 */
public class Departamento {
    private String id;
    private String tipo;
    private String nombre;
    private String domicilio;
    private String ciudad;
    private String codigoPostal;
    private String provincia;
    private String pais;
    
    /**
     * constructor de departamentos
     */
    public Departamento(){
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
    public Departamento(String id, String tipo, String nombre, String domicilio, String ciudad, String codigoPostal, String provincia, String pais) {
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
    
}
