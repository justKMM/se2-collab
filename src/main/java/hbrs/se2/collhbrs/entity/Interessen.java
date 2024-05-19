package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "interessen", schema = "public")
public class Interessen {
    private Student student;
    private int laufendeNummer;
    private String interessen;

    @Id
    @ManyToOne
    @JoinColumn(name = "studentid", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    public int getLaufendeNummer() {
        return laufendeNummer;
    }

    public void setLaufendeNummer(int laufendeNummer) {
        this.laufendeNummer = laufendeNummer;
    }

    @Basic
    @Column(name = "interessen", length = 256)
    public String getInteressen() {
        return interessen;
    }

    public void setInteressen(String interessen) {
        this.interessen = interessen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interessen that = (Interessen) o;
        return laufendeNummer == that.laufendeNummer &&
                Objects.equals(student, that.student) &&
                Objects.equals(interessen, that.interessen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, laufendeNummer, interessen);
    }
}