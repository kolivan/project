package base.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ann on 12.05.2015.
 */
@Entity
@Table(name = "book_journal")
public class JournalOperation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="journal_seq_gen")
    @SequenceGenerator(name="journal_seq_gen", sequenceName="JOURNAL_SEQ")
    @Column(name="journalId")
    private Long journalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operationId", nullable = false)
    private Operation operation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;


    @Temporal(TemporalType.DATE)
    @Column(name = "operationDate",nullable = false)
    private Date operationDate;

    @Column(name="comment", length = 150)
    private String comment;

    public JournalOperation() {

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
