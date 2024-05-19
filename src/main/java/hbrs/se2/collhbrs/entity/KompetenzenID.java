package hbrs.se2.collhbrs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class KompetenzenID implements java.io.Serializable {
    private static final long serialVersionUID = 4232012050114185395L;
    @NotNull
    @Column(name = "studentid", nullable = false)
    private Long studentID;

    @NotNull
    @Column(name = "laufende_nummer", nullable = false)
    private Integer laufendeNummer;

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentid) {
        this.studentID = studentid;
    }

    public Integer getLaufendeNummer() {
        return laufendeNummer;
    }

    public void setLaufendeNummer(Integer laufendeNummer) {
        this.laufendeNummer = laufendeNummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KompetenzenID entity = (KompetenzenID) o;
        return Objects.equals(this.studentID, entity.studentID) &&
                Objects.equals(this.laufendeNummer, entity.laufendeNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, laufendeNummer);
    }

}