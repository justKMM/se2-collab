package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.DegreeProgramm;
import hbrs.se2.collhbrs.model.entity.Student;

public class DegreeProgrammDTO {

    private Student student;
    private int serialNumber;
    private String degreeProgrammName;

    public DegreeProgrammDTO(DegreeProgramm entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.degreeProgrammName = entity.getDegreeProgrammName();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDegreeProgrammName() {
        return degreeProgrammName;
    }

    public void setDegreeProgrammName(String degreeProgrammName) {
        this.degreeProgrammName = degreeProgrammName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
