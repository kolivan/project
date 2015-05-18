package base.daos;

import base.entities.Genre;

import java.util.List;

/**
 * Created by Ann on 10.05.2015.
 */
public interface GenreDao {
    public Genre addGenre(String name);
    public void deleteBook(Genre genre);
    public  List<Genre> getAllGenre();
    /*void updateGenre(Genre newGenre);
    Genre getGenreById(Long id);
    List<Genre> getGenreNameLike(String name);
    List<Genre> getGenreBookLike(String nameBook);
    List<Genre> getGenreAuthorLike(String nameAuthor);
    */
}
