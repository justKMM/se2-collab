package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Skill;
import hbrs.se2.collhbrs.model.entity.Student;

public class SkillDTO {

    private Student student;
    private int serialNumber;
    private String skillName;

    public SkillDTO(Skill entity) {
        this.student = entity.getStudent();
        this.serialNumber = entity.getSerialNumber();
        this.skillName = entity.getSkillName();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
