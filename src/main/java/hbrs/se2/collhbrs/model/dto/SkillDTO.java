package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.traits.Skill;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillDTO {

    private Student student;
    private int serialNumber;
    private String skillName;

    public SkillDTO(Skill entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.skillName = entity.getSkillName();
    }

}
