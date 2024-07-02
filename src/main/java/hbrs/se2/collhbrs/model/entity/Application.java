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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application application = (Application) o;
        return applicationID == application.applicationID &&
                Objects.equals(student, application.student) &&
                Objects.equals(vacancy, application.vacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationID, student, vacancy);
    }
}
