package hbrs.se2.collhbrs.entity.ids;

import hbrs.se2.collhbrs.entity.Student;

import java.util.Objects;

public class DegreeProgrammID {
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
        DegreeProgrammID degreeProgrammID = (DegreeProgrammID) o;
        return serialNumber == degreeProgrammID.serialNumber &&
                Objects.equals(student, degreeProgrammID.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, serialNumber);
    }
}