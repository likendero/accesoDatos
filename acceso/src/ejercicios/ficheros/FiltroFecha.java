/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ficheros;

import java.io.File;
import java.util.GregorianCalendar;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author likendero
 */
public class FiltroFecha extends FileFilter {
    private GregorianCalendar fechaIni,fechaFinal;
    /**
     * metodo constructor con fecha de inicio y fecha de fin
     * @param fechaIni
     * @param fechaFinal 
     */
    public FiltroFecha(GregorianCalendar fechaIni, GregorianCalendar fechaFinal){
        this.fechaIni = fechaIni;
        this.fechaFinal = fechaFinal;
        
    }
    
    @Override
    public boolean accept(File f) {
        if (fechaIni.getTime().getTime() > f.lastModified() &&
            fechaFinal.getTime().getTime() <= f.lastModified()
                ) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "sirve para filtrar entre dos fechas";
    }
    
}
