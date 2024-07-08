package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.traits.DegreeProgramm;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DegreeProgrammDTO {

    private Student student;
    private int serialNumber;
    private String degreeProgrammName;

    public DegreeProgrammDTO(DegreeProgramm entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.degreeProgrammName = entity.getDegreeProgramName();
    }

    public String toString() {
        return "DegreeProgrammDTO{" +
                "student=" + student +
                ", serialNumber=" + serialNumber +
                ", degreeProgrammName='" + degreeProgrammName + '\'' +
                '}';
    }

}
