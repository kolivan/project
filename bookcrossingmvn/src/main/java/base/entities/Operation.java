package base.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ann on 07.05.2015.
 */
@Entity
@Table(name="operation", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name") })
public class Operation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="operation_seq_gen")
    @SequenceGenerator(name="operation_seq_gen", sequenceName="OPERATION_SEQ")
    @Column(name="operationId")
    private Long operationId;

    @Column(name="name", length = 64)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "operation")
    private Set<JournalOperation> journalOperations = new HashSet<JournalOperation>(0);



    public Long getOperationId() {

        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    /* @OneToMany(fetch = FetchType.EAGER, mappedBy = "operation")
        private Set<Journal> journals = new HashSet<Journal>(0);

        public Long getOperationId() {
            return operationId;
        }

        public void setOperationId(Long operationId) {
            this.operationId = operationId;
        }
    */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
