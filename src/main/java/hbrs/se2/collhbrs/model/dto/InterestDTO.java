package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.traits.Interest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class InterestDTO implements Serializable {

    private Student student;
    private int serialNumber;
    private String interestName;

    public InterestDTO(Interest entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.interestName = entity.getInterestName();
    }

}
