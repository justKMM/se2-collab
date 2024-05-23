package hbrs.se2.collhbrs.entity;

import hbrs.se2.collhbrs.entity.ids.InterestID;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "interessen", schema = "public")
@IdClass(InterestID.class)
public class Interest implements Serializable {
    private Student student;
    private int serialNumber;
    private String interestName;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "interessen", length = 256)
    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

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