/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miCodigo;
import hibernate.entity.Total;
import hibernate.util.HibernateUtil;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author Javier Gonzalez Rives
 */
public class Funciones {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        try{
            SessionFactory fac = HibernateUtil.getSessionFactory();
            Session sesion = fac.openSession();
            
            Query query = sesion.createQuery("select new hibernate.entity.Total(max(e.salario), avg(e.salario)) from "
                    + "Empleados e group by departamentos");
            Iterator<?> it = query.iterate();
            Total total = (Total) it.next();
            System.out.println("salario maximo: " + total.getSumatoria() + " media: " + total.getMedia());
            sesion.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
