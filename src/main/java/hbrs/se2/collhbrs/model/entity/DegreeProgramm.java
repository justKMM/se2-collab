package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.DegreeProgrammID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "studiengang", schema = "public")
@IdClass(DegreeProgrammID.class)
public class DegreeProgramm implements Serializable {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeProgramm degreeProgramm = (DegreeProgramm) o;
        return serialNumber == degreeProgramm.serialNumber &&
                Objects.equals(student, degreeProgramm.student) &&
                Objects.equals(degreeProgrammName, degreeProgramm.degreeProgrammName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, student, degreeProgrammName);
    }
}
