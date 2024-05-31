package hbrs.se2.collhbrs.model.dto.imp;

import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.SkillDTO;
import hbrs.se2.collhbrs.model.entity.Skill;

public class SkillDTOImpl implements SkillDTO {
    private StudentDTO student;
    private int serialNumber;
    private String skillName;

    @Override
    public StudentDTO getStudent() {
        return student;
    }

    @Override
    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String getSkillName() {
        return skillName;
    }

    @Override
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    @Override
    public Skill getEntity() {
        Skill skill = new Skill();
        skill.setStudent(student.getEntity());
        skill.setSerialNumber(serialNumber);
        skill.setSkillName(skillName);
        return skill;
    }
}