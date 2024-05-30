package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.FirstNameID;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vorname", schema = "public")
@IdClass(FirstNameID.class)
public class FirstName implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Id
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;

    @Basic
    @Column(name = "vorname", length = 128, nullable = false)
    private String firstNameName;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirstNameName() {
        return firstNameName;
    }

    public void setFirstNameName(String firstNameName) {
        this.firstNameName = firstNameName;
    }

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