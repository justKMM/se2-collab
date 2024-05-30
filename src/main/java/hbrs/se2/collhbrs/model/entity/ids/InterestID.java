package hbrs.se2.collhbrs.model.entity.ids;

import hbrs.se2.collhbrs.model.entity.Student;

import java.io.Serializable;
import java.util.Objects;

public class InterestID implements Serializable {
    private int serialNumber;
    private Student student;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestID interestID = (InterestID) o;
        return serialNumber == interestID.serialNumber &&
                Objects.equals(student, interestID.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, serialNumber);
    }
}