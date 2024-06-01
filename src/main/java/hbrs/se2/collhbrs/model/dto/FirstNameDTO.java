package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.FirstName;

public interface FirstNameDTO {
    StudentDTO getStudent();

    void setStudent(StudentDTO student);

    int getSerialNumber();

    void setSerialNumber(int serialNumber);

    String getFirstNameName();

    void setFirstNameName(String firstNameName);

    FirstName getEntity();
}