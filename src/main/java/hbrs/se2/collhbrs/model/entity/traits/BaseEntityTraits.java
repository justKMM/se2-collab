package hbrs.se2.collhbrs.model.entity.traits;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntityTraits<T, ID> extends BaseTraits implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public abstract ID getId();

}