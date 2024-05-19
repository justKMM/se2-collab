package hbrs.se2.collhbrs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class InteressenId implements java.io.Serializable {
    private static final long serialVersionUID = 6563205248753182671L;
    @NotNull
    @Column(name = "studentid", nullable = false)
    private Long studentid;

    @NotNull
    @Column(name = "laufende_nummer", nullable = false)
    private Integer laufendeNummer;

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
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
        InteressenId entity = (InteressenId) o;
        return Objects.equals(this.studentid, entity.studentid) &&
                Objects.equals(this.laufendeNummer, entity.laufendeNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentid, laufendeNummer);
    }

}