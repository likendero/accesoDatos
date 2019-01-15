/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miCodigo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Javier Gonzalez Rives
 */
public class MultivalorGenerico {
    public static void main(String[] args) {
        SessionFactory factory = hibernate.util.HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        Query query = session.createQuery("select ");
    }
}
