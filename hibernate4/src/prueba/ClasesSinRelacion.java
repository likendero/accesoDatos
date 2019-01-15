package prueba;
import empresaz.entity.Departamentos;
import empresaz.entity.Empleados;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 *
 * @author javier Gonzalez Rives
 */
public class ClasesSinRelacion {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        // creacion de la sesion
        SessionFactory factory = empresaz.util.HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        // proceso
        Query query = session.createQuery("from Departamentos d, Empleados e "
                + "where d.deptNo = e.deptNo ");
        Iterator<?> it = query.iterate();
        
        while(it.hasNext()){
            Object[] datos = (Object[])it.next();
            Departamentos dep = (Departamentos) datos[0];
            Empleados emp = (Empleados) datos[1];
            System.out.println("empleado: " + emp.getEmpNo() + " departamento: " + dep.getDeptNo());
        }
        session.close();
    }
}
