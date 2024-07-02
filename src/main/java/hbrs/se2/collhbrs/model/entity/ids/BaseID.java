package hbrs.se2.collhbrs.model.entity.ids;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public abstract class BaseID implements Serializable {


    private int serialNumber;

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseID baseID = (BaseID) o;
        return serialNumber == baseID.serialNumber;
    }
}