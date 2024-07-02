package hbrs.se2.collhbrs.model.entity.ids;

import hbrs.se2.collhbrs.model.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class DegreeProgrammID extends BaseID implements Serializable {
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeProgrammID degreeProgrammID = (DegreeProgrammID) o;
        return getSerialNumber() == degreeProgrammID.getSerialNumber() &&
                Objects.equals(student, degreeProgrammID.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, getSerialNumber());
    }
}