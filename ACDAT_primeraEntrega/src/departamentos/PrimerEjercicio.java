package departamentos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

/**
 *
 * @author Javier Gonzalez Rives
 */
public class PrimerEjercicio {
    public static void main(String[] args) {
        File dir = new File("src/departamentos/departamento.dat");
        Departamentos dep = new Departamentos();
        // definicion de atributos
        dep.setId("0001");
        dep.setCiudad("Almeria");
        dep.setCodigoPostal("04007");
        dep.setDomicilio("calle el peru nº 12");
        dep.setNombre("recursos humanos");
        dep.setPais("Espana");
        dep.setTipo("patata");
        dep.setProvincia("alemria");
        ArrayList<Departamentos> list = new ArrayList<Departamentos>();
        list.add(dep);
        list.add(dep);
        try{
            // escritura y lectura de comprobacion
            dep.saveData("departamento", "src/departamentos");
            System.out.println(Departamentos.leerFichero(dir).toString());
            Departamentos.saveDataList(list,new File("src/departamentos"),"departamentosList");
            System.out.println(Departamentos.readDataList(new File("src/departamentos","departamentosList.dat")).get(0).toString());
        }catch(ClassNotFoundException cl){
            cl.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
