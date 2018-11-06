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
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author likendero
 */
public class FicheroAXml {
    private static final File dir = new File("src/departamentos/departamento.dat");
    public static void main(String[] args) {
        Departamentos dep = null;
        try{
            // lectura del fichero
            dep = Departamentos.leerFichero(dir);
            // salida comprobacion
            System.out.println(dep.toString());
            // escritura de xml
            dep.saveXML("departamento2", "src/departamentos");
        }catch(NullPointerException nu){
            System.out.println("Error, el departamento es nulo");
        }catch(ClassNotFoundException cl){
            System.out.println("la clase no se encontrado");
            cl.printStackTrace();
        }catch(IOException io){
            System.out.println("error en la lectura");
            io.printStackTrace();
        }catch(ParserConfigurationException par){
            System.out.println("error en la configuracion del parser");
            par.printStackTrace();
        }catch(TransformerException tr){
            System.out.println("ha sucedido un error durante la transformacion");
            tr.printStackTrace();
        }
        
    }
}
