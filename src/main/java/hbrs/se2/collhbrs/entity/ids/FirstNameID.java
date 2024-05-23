package hbrs.se2.collhbrs.entity.ids;

import hbrs.se2.collhbrs.entity.Student;

import java.util.Objects;

public class FirstNameID {
    private Student student;
    private int serialNumber;

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
        FirstNameID firstNameID = (FirstNameID) o;
        return serialNumber == firstNameID.serialNumber &&
                Objects.equals(student, firstNameID.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, serialNumber);
    }
}