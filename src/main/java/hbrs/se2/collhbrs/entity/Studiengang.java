package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "studiengang", schema = "public")
public class Studiengang {
    private Student student;
    private int laufendeNummer;
    private String studiengang;

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
    @Column(name = "studiengang", length = 256, nullable = false)
    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studiengang that = (Studiengang) o;
        return laufendeNummer == that.laufendeNummer &&
                Objects.equals(student, that.student) &&
                Objects.equals(studiengang, that.studiengang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, laufendeNummer, studiengang);
    }
}
