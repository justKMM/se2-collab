package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Student;

import java.time.LocalDate;

public interface StudentDTO {
    long getStudentID();
    void setStudentID(long studentID);
    UserDTO getUser();
    void setUser(UserDTO user);
    String getLastName();
    void setLastName(String lastName);
    LocalDate getBirthdate();
    void setBirthdate(LocalDate birthdate);
    Student getEntity();
}