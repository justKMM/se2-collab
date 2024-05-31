package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Interest;

public interface InterestDTO {
    StudentDTO getStudent();
    void setStudent(StudentDTO student);
    int getSerialNumber();
    void setSerialNumber(int serialNumber);
    String getInterestName();
    void setInterestName(String interestName);
    Interest getEntity();
}