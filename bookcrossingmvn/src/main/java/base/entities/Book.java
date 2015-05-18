package base.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ann on 07.05.2015.
 */
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="book_seq_gen")
    @SequenceGenerator(name="book_seq_gen", sequenceName="BOOK_SEQ")
    @Column(name="bookId")
    private Long bookId;

    @Column(name="name", length = 64)
    private String name;

    @Column(name="language", length = 64)
    private String language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId") )
    private Set<Author> authors = new HashSet<Author>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "genreId") )
    private Set<Genre> genres = new HashSet<Genre>();

    public Book(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public Book() {
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    private Set<JournalOperation> journalOperations = new HashSet<JournalOperation>(0);


    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    private Set<Journal> journals = new HashSet<Journal>(0);

    public Set<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Set<Journal> journals) {
        this.journals = journals;
    }*/

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }


}
