package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Interest;

public interface InterestDTO {
    StudentDTO getStudent();

    int getSerialNumber();

    String getInterestName();
}