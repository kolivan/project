package base.entities;

/**
 * Created by Vadim on 22.03.2015.
 */
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="role_seq_gen")
    @SequenceGenerator(name="role_seq_gen", sequenceName="ROLE_SEQ")
    @Column(name="roleId")
    private Long roleId;

    public Role() {
    }

    @Column(name="name", length = 64)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users = new HashSet<User>(0);

    public Role(String roleName) {
        this.name = roleName;
    }

    public String getRoleName() {
        return name;
    }

    public void setRoleName(String roleName) {
        this.name = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getAuthority() {
        return name;
    }


    // Getter and Setter methods
}