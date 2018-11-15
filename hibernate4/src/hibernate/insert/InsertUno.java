/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.insert;
import empresaz.entity.Departamentos;
import empresaz.util.HibernateUtil;
import org.hibernate.*;
//import org.hibernate.classic.Session;
/**
 *
 * @author windiurno
 */
public class InsertUno {
    /**
     * metodo principal de la ejecion
     * @param args 
     */
    public static void main(String[] args) {
        SessionFactory fac = HibernateUtil.getSessionFactory();
        Session sesison = fac.openSession();
        try{
            // creacion de la transaccion
            System.out.println("creacion transaccion");
            Transaction trans = sesison.beginTransaction();
            // creacion de objeto trnasitorio
            Departamentos dep = new Departamentos();
            // introduccion de los campos
            dep.setDeptNo((byte)52);
            dep.setDnombre("MARKETING");
            dep.setLoc("GUADALAJARA");
            sesison.save(dep);
            trans.commit();
            
        }catch(HibernateException hib){
            System.out.println("ha habido un error");
            hib.printStackTrace();
        }finally{
            sesison.close();
            System.out.println("fin");
        }
    }
}
