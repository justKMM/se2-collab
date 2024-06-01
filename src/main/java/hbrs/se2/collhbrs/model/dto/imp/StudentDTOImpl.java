package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Student;

import java.time.LocalDate;

public class StudentDTOImpl implements StudentDTO {
    private long studentID;
    private UserDTOImpl user;
    private String lastName;
    private LocalDate birthdate;

    @Override
    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    @Override
    public UserDTO getUser() {
        return user;
    }


    public void setUser(UserDTOImpl user) {
        this.user = user;
    }

    @Override
    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }


    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public Student getEntity() {
        Student student = new Student();
        student.setStudentID(studentID);
        student.setUser(user.getEntity());
        student.setLastName(lastName);
        student.setBirthdate(birthdate);
        return student;
    }
}