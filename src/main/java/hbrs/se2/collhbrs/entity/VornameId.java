package hbrs.se2.collhbrs.entity;

import java.io.Serializable;
import java.util.Objects;

public class VornameId implements Serializable {
    private Student student;
    private int laufendeNummer;

    public VornameId() {}

    public VornameId(Student student, int laufendeNummer) {
        this.student = student;
        this.laufendeNummer = laufendeNummer;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public int getLaufendeNummer() {
        return laufendeNummer;
    }
    public void setLaufendeNummer(int laufendeNummer) {
        this.laufendeNummer = laufendeNummer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VornameId vornameId = (VornameId) o;
        return laufendeNummer == vornameId.laufendeNummer &&
                Objects.equals(student, vornameId.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, laufendeNummer);
    }
}