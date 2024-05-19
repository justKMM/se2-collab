package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "kompetenzen", schema = "public")
public class Kompetenzen {
    private Student student;
    private int laufendeNummer;
    private String kompetenzen;

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
    @Column(name = "kompetenzen", length = 256)
    public String getKompetenzen() {
        return kompetenzen;
    }

    public void setKompetenzen(String kompetenzen) {
        this.kompetenzen = kompetenzen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kompetenzen that = (Kompetenzen) o;
        return laufendeNummer == that.laufendeNummer &&
                Objects.equals(student, that.student) &&
                Objects.equals(kompetenzen, that.kompetenzen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, laufendeNummer, kompetenzen);
    }
}
