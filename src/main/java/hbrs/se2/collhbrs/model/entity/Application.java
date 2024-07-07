package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "bewirbt", schema = "public")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewirbtid")
    private long applicationID;

    @ManyToOne
    @JoinColumn(name = "studentid")
    @Getter
    private Student student;

    @ManyToOne
    @JoinColumn(name = "stellenausschreibungid")
    @Getter
    private Vacancy vacancy;

    @Basic
    @Column(name = "anschreiben", columnDefinition = "TEXT")
    private String coverLetter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return applicationID == that.applicationID &&
                Objects.equals(student, that.student) &&
                Objects.equals(vacancy, that.vacancy) &&
                Objects.equals(coverLetter, that.coverLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationID, student, vacancy, coverLetter);
    }
}
