package base.daos;

import base.entities.Author;

import java.util.List;

/**
 * Created by Ann on 10.05.2015.
 */
public interface AuthorDao {
        Author createAuthor();
        void deleteAuthor(Long id);
        void updateAuthor(Author newAuthor);
        Author getAuthorById(Long id);
        List<Author> getAuthorNameLike(String name);
        List<Author> getAuthorBookLike(String name);
        List<Author> getAuthorGenreLike(String name);
        List<Author> getAllBooks();

}
