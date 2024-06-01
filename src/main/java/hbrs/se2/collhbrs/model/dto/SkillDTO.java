package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Skill;

public interface SkillDTO {
    StudentDTO getStudent();

    int getSerialNumber();

    String getSkillName();

}