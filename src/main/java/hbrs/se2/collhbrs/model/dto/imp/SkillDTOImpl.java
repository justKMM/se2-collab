package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.SkillDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Skill;

public class SkillDTOImpl implements SkillDTO {
    private StudentDTOImpl student;
    private int serialNumber;
    private String skillName;

    @Override
    public StudentDTO getStudent() {
        return student;
    }


    public void setStudent(StudentDTOImpl student) {
        this.student = student;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }


    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String getSkillName() {
        return skillName;
    }


    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


    public Skill getEntity() {
        Skill skill = new Skill();
        skill.setStudent(student.getEntity());
        skill.setSerialNumber(serialNumber);
        skill.setSkillName(skillName);
        return skill;
    }
}