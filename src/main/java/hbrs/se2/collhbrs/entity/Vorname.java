package hbrs.se2.collhbrs.entity;

import hbrs.se2.collhbrs.entity.IDs.VornameId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "vorname", schema = "public")
@IdClass(VornameId.class)
public class Vorname {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Id
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int laufendeNummer;

    @Basic
    @Column(name = "vorname", length = 128, nullable = false)
    private String vorname;

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