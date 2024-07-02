package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class StudentDTO extends UserDTO {

    private long studentID;
    private User user;
    private String lastName;
    private LocalDate birthdate;

    public StudentDTO(Student entity) {
        super(entity.getUser());
        this.user = entity.getUser();
        this.studentID = entity.getStudentID();
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


}