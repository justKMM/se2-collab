package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.DegreeProgramm;

public interface DegreeProgrammDTO {
    StudentDTO getStudent();

    void setStudent(StudentDTO student);

    int getSerialNumber();

    void setSerialNumber(int serialNumber);

    String getDegreeProgrammName();

    void setDegreeProgrammName(String degreeProgrammName);

    DegreeProgramm getEntity();
}