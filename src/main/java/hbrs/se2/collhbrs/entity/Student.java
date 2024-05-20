package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "public")
public class Student {
    private long studentID;
    private Benutzer benutzer;
    private String nachname;
    private LocalDate geburtsdatum;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid", length = 64, nullable = false)
    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    @OneToOne
    @JoinColumn(name = "benutzerid", nullable = false, unique = true)
    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    @Basic
    @Column(name = "nachname", length = 128, nullable = false)
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "geburtsdatum")
    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID &&
                Objects.equals(benutzer, student.benutzer) &&
                Objects.equals(nachname, student.nachname) &&
                Objects.equals(geburtsdatum, student.geburtsdatum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, benutzer, nachname, geburtsdatum);
    }
}
