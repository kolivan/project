package base.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ann on 07.05.2015.
 */
@Entity
@Table(name="genre", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name") })
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="genre_seq_gen")
    @SequenceGenerator(name="genre_seq_gen", sequenceName="GENRE_SEQ")
    @Column(name="genreId")
    private Long genreId;

    @Column(name="name", length = 64)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "genres")
    private Set<Book> books = new HashSet<Book>(0);

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Genre(String name) {
        this.name = name;
    }
}
