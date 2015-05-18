package base.daos;

import base.entities.Book;

/**
 * Created by Ann on 08.05.2015.
 */
public interface BookDAO {
    public Book addBook(String bookName, String bookLanguage);
    public void deleteBook(Book book);
   /* public void updateBook(Book newBook);
    public List<Book> getAllBooks();
    /*Book getBookById(Long id);
    List<Book> getBoookNameLike(String name);
    List<Book> getBookAuthorLike(String nameAuthor);
    List<Book> getBookGenreLike(String nameGenre);
    List<Book> getBookOperationLike(String nameOperation);
    */
}
