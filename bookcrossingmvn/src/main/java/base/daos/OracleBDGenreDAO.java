package base.daos;


import base.entities.Genre;
import base.entities.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Ann on 17.05.2015.
 */
public class OracleBDGenreDAO implements GenreDao {
    private SessionFactory sessionFactory;
    public Genre addGenre(String name)
    {
        try {
            Genre genre = new Genre(name);
            SessionFactory factory = HibernateUtil.getSessionFactory();

            boolean saved = true;
            Session session = null;
            try {
                session = factory.openSession();
                session.beginTransaction();
                session.save(genre);
                session.getTransaction().commit();
                saved = true;
            } catch (Exception e) {
                saved = false;//TODO : check ig user already exists
                System.out.println("OracleDBUserDAO.createUser exception!");

            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            if (saved) {
                return genre;
            } else {
                return null;
            }}
        catch (Exception e )
        {
            int i=0;
        }
        return null;
    }
    public void deleteBook(Genre genre)
    {

        sessionFactory.getCurrentSession().delete(genre.getGenreId());
    }

    public List<Genre> getAllGenre() {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = null;
        List<Genre> list = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query =session.createQuery("FROM base.entities.Genre");
            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("OracleDBQuestionDAO.getAllQuestions exception!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }
}
