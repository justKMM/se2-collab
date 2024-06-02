package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;

import java.time.LocalDate;

public class StudentDTO {

    private long studentID;
    private User user;
    private String lastName;
    private LocalDate birthdate;

    public StudentDTO(Student entity) {
        this.studentID = entity.getStudentID();
        this.user = entity.getUser();
        this.lastName = entity.getLastName();
        this.birthdate = entity.getBirthdate();
    }

    public Student getStudent() {
        Student student = new Student();
        student.setStudentID(studentID);
        student.setUser(user);
        student.setLastName(lastName);
        student.setBirthdate(birthdate);
        return student;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}