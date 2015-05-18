package base.entities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Locale;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
//    private static final String HIBERNATE_CFG = "hibernateAnnotations.cfg.xml";

    private static SessionFactory buildSessionFactory()
    {

        Locale.setDefault(Locale.ENGLISH);
        Configuration cfg = new Configuration().configure();
        cfg.addAnnotatedClass(Author.class);
        cfg.addAnnotatedClass(Role.class);
        cfg.addAnnotatedClass(Genre.class);
        cfg.addAnnotatedClass(Book.class);
        cfg.addAnnotatedClass(JournalOperation.class);
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Operation.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                cfg.getProperties()).build();
        SessionFactory factory = null;
        try {
            factory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.out.println("HibernateUtil.buildSessionFactory exception!");
        }

        return factory;
    }

    public static SessionFactory getSessionFactory() {
//        synchronized(sessionFactory) {
        return sessionFactory;
//        }
    }
}
