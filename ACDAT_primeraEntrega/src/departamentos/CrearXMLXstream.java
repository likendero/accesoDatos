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

/**
 *
 * @author Javier Gonzalez Rives
 */
public class CrearXMLXstream {
    private static final File directorio = new File("src/departamentos/departamento.dat");
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // creacion de un nuevo Departamento 
        Departamentos departamento;
        try{
            // captura del valor del departamento
            departamento = Departamentos.leerFichero(directorio);
            // escritura del fichero
            departamento.saveXMLXstream("departamentoXstream", "src/departamentos");
            // control de las posibles excepciones
        }catch(ClassNotFoundException cl){
            System.out.println("clase no encontrada");
            cl.printStackTrace();
        }catch(IOException io){
            System.out.println("error en la escritura");
            io.printStackTrace();
        }
    }
}
