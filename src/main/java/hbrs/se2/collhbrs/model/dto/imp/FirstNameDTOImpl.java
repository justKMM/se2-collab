package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.FirstNameDTO;
import hbrs.se2.collhbrs.model.entity.FirstName;

public class FirstNameDTOImpl implements FirstNameDTO {
    private StudentDTO student;
    private int serialNumber;
    private String firstNameName;

    @Override
    public StudentDTO getStudent() {
        return student;
    }

    @Override
    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String getFirstNameName() {
        return firstNameName;
    }

    @Override
    public void setFirstNameName(String firstNameName) {
        this.firstNameName = firstNameName;
    }
    @Override
    public FirstName getEntity() {
        FirstName firstName = new FirstName();
        firstName.setStudent(student.getEntity());
        firstName.setSerialNumber(serialNumber);
        firstName.setFirstNameName(firstNameName);
        return firstName;
    }
}