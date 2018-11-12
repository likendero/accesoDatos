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
import java.io.FileOutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author likendero
 */
public class TranformarXsl {
    /**
     * metodo principal crear html
     * @param args 
     */
    public static void main(String[] args) {
        File dirXsl = new File("src/departamentos/departamentosList.xsl");
        File dirXml = new File("src/departamentos/departamentosXstreamList.xml");
        File dirSalida = new File("src/departamentos/departamentosList.html");
        try{
            // creacion de los elementos source
            Source sourceXsl = new StreamSource(dirXsl);
            Source sourceXml = new StreamSource(dirXml);
           
            // creacion del transform factory
            TransformerFactory fac = TransformerFactory.newInstance();
            // creacion del transformer
            Transformer trans = fac.newTransformer(sourceXsl);
            trans.transform(sourceXml, new StreamResult(dirSalida));
        }catch(TransformerException tr){
        }catch(TransformerFactoryConfigurationError tra){
        }
    }
}
