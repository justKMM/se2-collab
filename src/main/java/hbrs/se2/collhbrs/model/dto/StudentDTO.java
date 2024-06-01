package hbrs.se2.collhbrs.model.dto;

import java.time.LocalDate;

public interface StudentDTO {
    long getStudentID();

    UserDTO getUser();

    String getLastName();

    LocalDate getBirthdate();

}