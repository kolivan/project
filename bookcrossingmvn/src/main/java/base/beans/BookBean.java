package base.beans;

import base.daos.BookDAO;
import base.daos.GenreDao;
import base.daos.OracleBDBookDAO;
import base.entities.Book;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Ann on 10.05.2015.
 */
@ManagedBean(name = "bookBean",eager = true)
@RequestScoped
public class BookBean implements Serializable {
    private Book book;
    private BookDAO bookDAO;
    private GenreDao genreDao;
    public BookBean ()
    {
        bookDAO = new OracleBDBookDAO();
        book = new Book();
    }

    public String createBook(){
        try{
            Book new_book = bookDAO.addBook(book.getName(),book.getLanguage());

            try {
                if(new_book == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Book with this name already exists"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Your book successfully added!"));
                }
            } catch (Exception e ) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal Error", "Registration error! Try later."));
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "top?faces-redirect=true";}
        catch (Exception e)
        {
            int i=0;
        }
        return null;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
