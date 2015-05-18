package base.daos;

/**
 * Created by vadim on 24.04.15.
 */

import base.entities.HibernateUtil;
import base.entities.Role;
import base.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by vadim on 13.04.15.
 */
public class OracleDBRoleDAO implements RoleDAO {

    public Role createRole(String name) {
        Role role = new Role(name);

        SessionFactory factory = HibernateUtil.getSessionFactory();

        boolean saved = true;
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
            saved = true;
        } catch (Exception e) {
            System.out.println("OracleDBRoleDAO.createRole exception!");
            saved = false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if(saved) {
            return role;
        } else {
            return null;
        }
    }

    public void deleteRole(Long id) {
        Role role = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            role = (Role)session.load(User.class, id);
            session.delete(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBRoleDAO.deleteRole exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateRole(Role roleDetached) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.update(roleDetached);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBRoleDAO.updateRole exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Role getRoleById(Long id) {
        Role role = null;

        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            role = (Role)session.load(Role.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBRoleDAO.getRoleById exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return role;
    }

    public List<Role> getRoleNameLike(String name) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        List<Role> list = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Role r where r.name like :n");

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

    public List<Role> getAllRoles() {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        List<Role> list = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM base.entities.Role");

            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBRoleDAO.getAllRoles exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }
}
