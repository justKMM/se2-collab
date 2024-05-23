package hbrs.se2.collhbrs.entity;

import hbrs.se2.collhbrs.entity.ids.DegreeProgrammID;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "studiengang", schema = "public")
@IdClass(DegreeProgrammID.class)
public class DegreeProgramm {
    private Student student;
    private int serialNumber;
    private String degreeProgrammName;

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
    @Column(name = "studiengang", length = 256, nullable = false)
    public String getDegreeProgrammName() {
        return degreeProgrammName;
    }

    public void setDegreeProgrammName(String degreeProgrammName) {
        this.degreeProgrammName = degreeProgrammName;
    }

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
