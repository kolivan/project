package base.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ann on 07.05.2015.
 */
@Entity
@Table(name="author", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name") })
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="author_seq_gen")
    @SequenceGenerator(name="author_seq_gen", sequenceName="AUTHOR_SEQ")
    @Column(name="authorId")
    private Long authorId;

    @Column(name="name", length = 64)
    private String name;

    @Column(name="country", length = 64)
    private String country;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
    private Set<Book> books = new HashSet<Book>(0);

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
