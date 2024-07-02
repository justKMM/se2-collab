package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.FirstNameID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "vorname", schema = "public")
@IdClass(FirstNameID.class)
public class FirstName implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;

    @Basic
    @Column(name = "vorname", length = 128, nullable = false)
    private String firstNameName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstName firstName = (FirstName) o;
        return serialNumber == firstName.serialNumber &&
                Objects.equals(student, firstName.student) &&
                Objects.equals(this.firstNameName, firstName.firstNameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, serialNumber, firstNameName);
    }
}