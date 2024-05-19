package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "kontaktverknuepfung", schema = "public")
public class KontaktVerknuepfung {
    private long kontaktVerknuepfungID;
    private Unternehmen unternehmen;
    private Student student;
    private int bewertungsScoreStudent;
    private String beschreibungStudent;
    private int bewertungsScoreUnternehmen;
    private String beschreibungUnternehmen;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kontaktverknuepfungid", length = 64, nullable = false)
    public long getKontaktVerknuepfungID() {
        return kontaktVerknuepfungID;
    }

    public void setKontaktVerknuepfungID(long kontaktVerknuepfungID) {
        this.kontaktVerknuepfungID = kontaktVerknuepfungID;
    }

    @ManyToOne
    @JoinColumn(name = "studentid", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "unternehmenid", nullable = false)
    public Unternehmen getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    @Basic
    @Column(name = "bewertungsscore_student", length = 1)
    public int getBewertungsScoreStudent() {
        return bewertungsScoreStudent;
    }

    public void setBewertungsScoreStudent(int bewertungsScoreStudent) {
        this.bewertungsScoreStudent = bewertungsScoreStudent;
    }

    @Basic
    @Column(name = "beschreibung_student", length = 256)
    public String getBeschreibungStudent() {
        return beschreibungStudent;
    }

    public void setBeschreibungStudent(String beschreibungStudent) {
        this.beschreibungStudent = beschreibungStudent;
    }

    @Basic
    @Column(name = "bewertungsscore_unternehmen", length = 1)
    public int getBewertungsScoreUnternehmen() {
        return bewertungsScoreUnternehmen;
    }

    public void setBewertungsScoreUnternehmen(int bewertungsScoreUnternehmen) {
        this.bewertungsScoreUnternehmen = bewertungsScoreUnternehmen;
    }

    @Basic
    @Column(name = "beschreibung_unternehmen", length = 256)
    public String getBeschreibungUnternehmen() {
        return beschreibungUnternehmen;
    }

    public void setBeschreibungUnternehmen(String beschreibungUnternehmen) {
        this.beschreibungUnternehmen = beschreibungUnternehmen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KontaktVerknuepfung that = (KontaktVerknuepfung) o;
        return kontaktVerknuepfungID == that.kontaktVerknuepfungID &&
                bewertungsScoreStudent == that.bewertungsScoreStudent &&
                bewertungsScoreUnternehmen == that.bewertungsScoreUnternehmen &&
                Objects.equals(unternehmen, that.unternehmen) &&
                Objects.equals(student, that.student) &&
                Objects.equals(beschreibungStudent, that.beschreibungStudent) &&
                Objects.equals(beschreibungUnternehmen, that.beschreibungUnternehmen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kontaktVerknuepfungID, unternehmen, student, bewertungsScoreStudent, beschreibungStudent, bewertungsScoreUnternehmen, beschreibungUnternehmen);
    }
}
