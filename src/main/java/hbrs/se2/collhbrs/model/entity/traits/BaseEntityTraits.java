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
    @Column(name = "laufende_nummer", nullable = false)
    private int serialNumber;

    public abstract ID getId();
}