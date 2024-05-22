package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "bewirbt", schema = "public")
public class Application {
    private long applicationID;
    private Student student;
    private Vacancy vacancy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewirbtid")
    public long getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(long applicationID) {
        this.applicationID = applicationID;
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
    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

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
