package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.InterestID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interessen", schema = "public")
@IdClass(InterestID.class)
public class Interest extends BaseEntityTraits<Student, InterestID> {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Basic
    @Column(name = "interessen", length = 256)
    private String interestName;

    @Override
    public InterestID getId() {
        InterestID id = new InterestID();
        id.setStudent(student);
        id.setSerialNumber(super.getSerialNumber());
        return id;
    }

}