package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.SkillID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kompetenzen", schema = "public")
@IdClass(SkillID.class)
public class Skill extends BaseEntityTraits<Student, SkillID> {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Basic
    @Column(name = "kompetenzen", length = 256)
    private String skillName;

    @Override
    public SkillID getId() {
        SkillID id = new SkillID();
        id.setStudent(student);
        id.setSerialNumber(super.getSerialNumber());
        return id;
    }

    @Override
    public Student getEntity() {
        return student;
    }
}