package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.DegreeProgrammID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "studiengang", schema = "public")
@IdClass(DegreeProgrammID.class)
public class DegreeProgramm extends BaseEntityTraits<Student, DegreeProgrammID> {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Basic
    @Column(name = "studiengang", length = 256, nullable = false)
    private String degreeProgramName;

    @Override
    public DegreeProgrammID getId() {
        DegreeProgrammID id = new DegreeProgrammID();
        id.setStudent(student);
        id.setSerialNumber(super.getSerialNumber());
        return id;
    }
}