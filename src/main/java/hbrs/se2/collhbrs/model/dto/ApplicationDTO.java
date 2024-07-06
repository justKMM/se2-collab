package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ApplicationDTO implements Serializable {

    private long applicationID;
    private Student student;
    private Vacancy vacancy;

    public ApplicationDTO(Application application) {
        this.applicationID = application.getApplicationID();
        this.student = application.getStudent();
        this.vacancy = application.getVacancy();
    }
}
