package base.daos;

import base.entities.Book;
import base.entities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Ann on 10.05.2015.
 */
public class OracleBDBookDAO implements BookDAO {


    private SessionFactory sessionFactory;
    public Book addBook(String bookName, String bookLanguage)
    {
        try {
        Book book = new Book(bookName,bookLanguage);
        SessionFactory factory = HibernateUtil.getSessionFactory();

            boolean saved = true;
            Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(book);
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
            return book;
        } else {
            return null;
        }}
        catch (Exception e )
        {
            int i=0;
        }
    return null;
    }
    public void deleteBook(Book book)
    {

        sessionFactory.getCurrentSession().delete(book.getBookId());
    }
    /*public void updateBook(Book newBook)
    {
        sessionFactory.getCurrentSession().update(newBook);
    }
    public List<Book> getAllBooks() {
        List list = sessionFactory.getCurrentSession().createQuery("from Book").list();
        return list;
    }
    /*Book getBookById(Long id);
    List<Book> getBoookNameLike(String name);
    List<Book> getBookAuthorLike(String nameAuthor);
    List<Book> getBookGenreLike(String nameGenre);
    List<Book> getBookOperationLike(String nameOperation);
    List<Book> getAllBooks();*/
}
