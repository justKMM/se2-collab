package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "vorname", schema = "public")
public class Vorname {
    private Student student;
    private int laufendeNummer;
    private String vorname;

    @Id
    @ManyToOne
    @JoinColumn(name = "studentid", nullable = false)
    private Student getStudent() {
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
    @Column(name = "vorname", length = 128, nullable = false)
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vorname vorname = (Vorname) o;
        return laufendeNummer == vorname.laufendeNummer &&
                Objects.equals(student, vorname.student) &&
                Objects.equals(this.vorname, vorname.vorname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, laufendeNummer, vorname);
    }
}
