package hbrs.se2.collhbrs.model.entity.ids;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class ResponsibilitiesID extends BaseID implements Serializable {
    private Vacancy vacancy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponsibilitiesID responsibilitiesID = (ResponsibilitiesID) o;
        return getSerialNumber() == responsibilitiesID.getSerialNumber() &&
                Objects.equals(vacancy, responsibilitiesID.vacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancy, getSerialNumber());
    }
}