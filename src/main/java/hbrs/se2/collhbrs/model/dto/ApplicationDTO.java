package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationDTO {

    private long applicationID;
    private Student student;
    private Vacancy vacancy;
    private String coverLetter;

    public ApplicationDTO(Application application) {
        this.applicationID = application.getApplicationID();
        this.student = application.getStudent();
        this.vacancy = application.getVacancy();
        this.coverLetter = application.getCoverLetter();
    }

    public Application getApplication() {
        Application application = new Application();
        application.setApplicationID(applicationID);
        application.setStudent(student);
        application.setVacancy(vacancy);
        application.setCoverLetter(coverLetter);
        return application;
    }

    public String toString() {
        return "ApplicationDTO{" +
                "applicationID=" + applicationID +
                ", student=" + student +
                ", vacancy=" + vacancy +
                ", coverLetter='" + coverLetter + '\'' +
                '}';
    }
}
