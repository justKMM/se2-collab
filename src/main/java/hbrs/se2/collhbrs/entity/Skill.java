package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "kompetenzen", schema = "public")
public class Skill {
    private Student student;
    private int serialNumber;
    private String skillName;

    @Id
    @ManyToOne
    @JoinColumn(name = "studentid", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int laufendeNummer) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "kompetenzen", length = 256)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return serialNumber == skill.serialNumber &&
                Objects.equals(student, skill.student) &&
                Objects.equals(skillName, skill.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, student, skillName);
    }

}
