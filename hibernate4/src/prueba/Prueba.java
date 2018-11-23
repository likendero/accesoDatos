package prueba;
import empresaz.entity.Departamentos;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 *
 * @author javier Gonzalez Rives
 */
public class Prueba {
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args) {
        // creacion de la sesion
        SessionFactory factory = empresaz.util.HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        // proceso
        System.out.println("datos del departamento");
        Departamentos dep = new Departamentos();
        dep = (Departamentos)session.get(Departamentos.class,(byte) 10 );
        if(dep == null){
            System.out.println("el departamento no existe");
        }else{
            System.out.println("nombre dep " + dep.getDnombre());
            System.out.println("Localidad " + dep.getLoc());
        }
        session.close();
    }
}
