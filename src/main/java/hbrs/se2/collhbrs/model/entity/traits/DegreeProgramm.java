package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.DegreeProgrammID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "studiengang", schema = "public")
@IdClass(DegreeProgrammID.class)
public class DegreeProgramm extends BaseTraits implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;

    @Basic
    @Column(name = "studiengang", length = 256, nullable = false)
    private String degreeProgrammName;

    @Override
    public DegreeProgrammID getId() {
        DegreeProgrammID id = new DegreeProgrammID();
        id.setStudent(student);
        id.setSerialNumber(serialNumber);
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}