package hbrs.se2.collhbrs.entity;

import hbrs.se2.collhbrs.entity.IDs.InterestID;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "interessen", schema = "public")
@IdClass(InterestID.class)
public class Interest {
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

    public void setInterestName(String InterestName) {
        this.interestName = InterestName;
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