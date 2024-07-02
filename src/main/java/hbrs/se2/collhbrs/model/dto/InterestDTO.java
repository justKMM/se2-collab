package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Interest;
import hbrs.se2.collhbrs.model.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InterestDTO {

    private Student student;
    private int serialNumber;
    private String interestName;

    public InterestDTO(Interest entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.interestName = entity.getInterestName();
    }

}
