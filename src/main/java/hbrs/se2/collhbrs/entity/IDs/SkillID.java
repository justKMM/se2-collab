package hbrs.se2.collhbrs.entity.IDs;

import hbrs.se2.collhbrs.entity.Skill;

import java.util.Objects;

public class SkillID {
    private Skill skill;
    private int serialNumber;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillID skillID = (SkillID) o;
        return serialNumber == skillID.serialNumber &&
                Objects.equals(skill, skillID.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill, serialNumber);
    }
}