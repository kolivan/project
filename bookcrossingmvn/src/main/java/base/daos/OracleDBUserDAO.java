package base.daos;

/**
 * Created by vadim on 23.04.15.
 */

import base.entities.HibernateUtil;
import base.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vadim on 13.04.15.
 */
public class OracleDBUserDAO implements UserDAO {


    public User createUser(String name, String pass, String email) {
        try {

            User usr = new User(name, pass, email);

            SessionFactory factory = HibernateUtil.getSessionFactory();

            boolean fail = false;
            Session session = null;
            try {
                session = factory.openSession();
                session.beginTransaction();
                session.save(usr);
                session.getTransaction().commit();
            } catch (Exception e) {
                fail = true;//TODO : check ig user already exists
                System.out.println("OracleDBUserDAO.createUser exception!");

            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            if (fail) {
                return null;
            } else {
                return usr;
            }

        }
        catch (Exception e )
        {
            int i=0;
        }
        return  null;
    }

    public void deleteUser(Long id) {
        User user = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            user = (User)session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBUserDAO.deleteUser exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateUser(User userDetached) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.update(userDetached);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBUserDAO.updateUser exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User getUserById(Long id) {
        User user = null;

        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            user = (User)session.load(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBSectionDAO.getSectionById exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getUserNameLike(String name) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        List<User> list = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User u where u.name like :n");

            list = query.setParameter("n","%" + name + "%").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBSectionDAO.getAllUsers exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public List<User> getUsersByRole(Long roleId) {
        return null;
    }

    //    List<Role> getRoles(Long userId);
    public List<User> getAllUsers() {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        List<User> list = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM base.entities.User");

            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBSectionDAO.getAllUsers exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public User getUserByName(String name) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        List<User> list = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User u where u.userName = :usrName");

            list = query.setParameter("usrName",name).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBSectionDAO.getAllUsers exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if(list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
