package hbrs.se2.collhbrs.model.entity.traits;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseTraits implements Serializable {

    public abstract Object getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTraits that = (BaseTraits) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}