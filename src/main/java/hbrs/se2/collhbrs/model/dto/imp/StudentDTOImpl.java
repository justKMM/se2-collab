package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;

import java.time.LocalDate;

public class StudentDTOImpl implements StudentDTO {
    private long studentID;
    private UserDTO user;
    private String lastName;
    private LocalDate birthdate;

    @Override
    public long getStudentID() {
        return studentID;
    }

    @Override
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    @Override
    public UserDTO getUser() {
        return user;
    }

    @Override
    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    @Override
    public Student getEntity() {
        Student student = new Student();
        student.setStudentID(studentID);
        student.setUser(user.getEntity());
        student.setLastName(lastName);
        student.setBirthdate(birthdate);
        return student;
    }
}