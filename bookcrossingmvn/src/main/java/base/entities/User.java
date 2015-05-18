package base.entities;

/**
 * Created by Vadim on 22.03.2015.
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="userB", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name") })
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="user_seq_gen")
    @SequenceGenerator(name="user_seq_gen", sequenceName="USER_SEQ")
    @Column(name="userId")
    private Long userId;

    @Column(name="name", length = 64)
    private String name;

    @Column(name="pass", length = 64)
    private String pass;

    @Column(name="email", length = 64)
    private String email;


    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId") )
    private Set<Role> roles = new HashSet<Role>();

   /* @OneToMany(fetch = FetchType.EAGER, mappedBy = "operation")
    private Set<Journal> journals = new HashSet<Journal>(0);

    public Set<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Set<Journal> journals) {
        this.journals = journals;
    }
*/
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
   private Set<JournalOperation> journalOperations = new HashSet<JournalOperation>(0);

    public User(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}