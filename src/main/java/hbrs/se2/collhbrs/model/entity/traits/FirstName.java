package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.FirstNameID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vorname", schema = "public")
@IdClass(FirstNameID.class)
public class FirstName extends BaseEntityTraits<Student, FirstNameID> {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;

    @Basic
    @Column(name = "vorname", length = 128, nullable = false)
    private String firstNameName;

    @Override
    public FirstNameID getId() {
        FirstNameID id = new FirstNameID();
        id.setStudent(student);
        id.setSerialNumber(super.getSerialNumber());
        return id;
    }

    @Override
    public Student getEntity() {
        return student;
    }


}