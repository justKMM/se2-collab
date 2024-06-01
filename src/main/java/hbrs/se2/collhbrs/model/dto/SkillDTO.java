package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Skill;

public interface SkillDTO {
    StudentDTO getStudent();

    void setStudent(StudentDTO student);

    int getSerialNumber();

    void setSerialNumber(int serialNumber);

    String getSkillName();

    void setSkillName(String skillName);

    Skill getEntity();
}