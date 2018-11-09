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
import java.util.ArrayList;

/**
 *
 * @author likendero
 */
public class XstreamPrueba {
    public static void main(String[] args) {
        Departamentos dep1 = new Departamentos("111", "patata", "hawaii", "c/ el salvador",
                "bucarest", "22222222", "almeria", "espanna");
        Departamentos dep2 = new Departamentos("111", "patata", "hawaii", "c/ el salvador",
                "bucarest", "22222222", "almeria", "espanna");
        Departamentos dep3 = new Departamentos("111", "patata", "hawaii", "c/ el salvador",
                "bucarest", "22222222", "almeria", "espanna");
        ArrayList<Departamentos> deps = new ArrayList<Departamentos>();
        
        deps.add(dep1);
        deps.add(dep2);
        deps.add(dep3);
        
        try{
            XStream xstream = new XStream();
            xstream.alias("departamentos", ArrayList.class);
            xstream.alias("departamento", Departamentos.class);
            System.out.println(xstream.toXML(deps));
        }catch(Exception ex){
        }
    }
}