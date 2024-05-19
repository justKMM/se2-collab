package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "bewirbt", schema = "public")
public class Bewirbt {
    private long bewerbungID;
    private Student student;
    private Stellenausschreibung stellenausschreibung;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewirbtid")
    public long getBewerbungID() {
        return bewerbungID;
    }

    public void setBewerbungID(long bewerbungID) {
        this.bewerbungID = bewerbungID;
    }

    @ManyToOne
    @JoinColumn(name = "studentid")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "stellenausschreibungid")
    public Stellenausschreibung getStellenausschreibung() {
        return stellenausschreibung;
    }

    public void setStellenausschreibung(Stellenausschreibung stellenausschreibung) {
        this.stellenausschreibung = stellenausschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bewirbt bewirbt = (Bewirbt) o;
        return bewerbungID == bewirbt.bewerbungID &&
                Objects.equals(student, bewirbt.student) &&
                Objects.equals(stellenausschreibung, bewirbt.stellenausschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewerbungID, student, stellenausschreibung);
    }

}
