package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.FirstName;
import hbrs.se2.collhbrs.model.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FirstNameDTO {

    private Student student;
    private int serialNumber;
    private String firstNameName;

    public FirstNameDTO(FirstName entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.firstNameName = entity.getFirstNameName();
    }

}