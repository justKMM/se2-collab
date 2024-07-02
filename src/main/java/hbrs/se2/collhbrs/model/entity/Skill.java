package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.SkillID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "kompetenzen", schema = "public")
@IdClass(SkillID.class)
public class Skill implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;
    @Id
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serialNumber;
    @Basic
    @Column(name = "kompetenzen", length = 256)
    private String skillName;


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
