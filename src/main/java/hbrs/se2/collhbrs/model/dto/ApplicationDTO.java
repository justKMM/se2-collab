package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;

public class ApplicationDTO {
    private long applicationID;
    private Student student;
    private Vacancy vacancy;

    public ApplicationDTO(Application entity) {
        this.applicationID = applicationID;
        this.student = student;
        this.vacancy = vacancy;
    }

    public long getApplicationID() {
        return applicationID;
    }

    public Student getStudent() {
        return student;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setApplicationID(long applicationID) {
        this.applicationID = applicationID;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getTitle() {
        return vacancy.getTitel();
    }

    public String getDescription() {
        return vacancy.getDescription();
    }
}
