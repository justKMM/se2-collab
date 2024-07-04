package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "aufgaben", schema = "public")
@IdClass(ResponsibilitiesID.class)
public class Responsibilities extends BaseTraits implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "StellenausschreibungID", nullable = false)
    private Vacancy vacancy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;

    @Basic
    @Column(name = "aufgaben", length = 128, nullable = false)
    private String responsibilitiesName;

    @Override
    public ResponsibilitiesID getId() {
        ResponsibilitiesID id = new ResponsibilitiesID();
        id.setVacancy(vacancy);
        id.setSerialNumber(serialNumber);
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}