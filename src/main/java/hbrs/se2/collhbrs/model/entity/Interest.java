package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.InterestID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "interessen", schema = "public")
@IdClass(InterestID.class)
public class Interest implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;
    @Basic
    @Column(name = "interessen", length = 256)
    private String interestName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest that = (Interest) o;
        return serialNumber == that.serialNumber &&
                Objects.equals(student, that.student) &&
                Objects.equals(interestName, that.interestName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, student, interestName);
    }
}