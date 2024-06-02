package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Interest;
import hbrs.se2.collhbrs.model.entity.Student;

public class InterestDTO {

    private Student student;
    private int serialNumber;
    private String interestName;

    public InterestDTO(Interest entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.interestName = entity.getInterestName();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
