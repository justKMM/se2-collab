package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;

public class ApplicationDTO {

    private long aplicationId;
    private Student student;
    private Vacancy vacancy;

    public ApplicationDTO(Application application) {
        this.aplicationId = application.getApplicationID();
        this.student = application.getStudent();
        this.vacancy = application.getVacancy();
    }


    public long getAplicationId() {
        return aplicationId;
    }

    public void setAplicationId(long aplicationId) {
        this.aplicationId = aplicationId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
