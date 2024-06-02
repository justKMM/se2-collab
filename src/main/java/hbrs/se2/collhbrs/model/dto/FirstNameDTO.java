package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.FirstName;
import hbrs.se2.collhbrs.model.entity.Student;

public class FirstNameDTO {

    private Student student;
    private int serialNumber;
    private String firstNameName;

    public FirstNameDTO(FirstName entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.firstNameName = entity.getFirstNameName();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirstNameName() {
        return firstNameName;
    }

    public void setFirstNameName(String firstNameName) {
        this.firstNameName = firstNameName;
    }
}