package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Student;

import java.time.LocalDate;

public interface StudentDTO {
    long getStudentID();

    UserDTO getUser();

    String getLastName();

    LocalDate getBirthdate();

}