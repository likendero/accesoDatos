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
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author likendero
 */
public class DomMultiDepartamento {
    private static final File dir = new File("src/departamentos");
    // metodo principal
    public static void main(String[] args) {
        // creacion de una lista para alamcenar
        ArrayList<Departamentos> deps;
        try{
            // lectura del fichero
            deps = Departamentos.readDataList(new File(dir,"departamentosList.dat"));
            // escritura del fichero xml
            Departamentos.saveXMLList(deps, dir, "departamentosListXml");
        }catch(IOException io){
            System.out.println("error entrada salida");
            io.printStackTrace();
        }catch(ClassNotFoundException cl){
            System.out.println("clase no encontrada");
            cl.printStackTrace();
        }catch(ParserConfigurationException par){
            System.out.println("error en el parseo");
            par.printStackTrace();
        }catch(TransformerException tr){
            System.out.println("error en la transformacion");
            tr.printStackTrace();
        }
        
        
    }
}
