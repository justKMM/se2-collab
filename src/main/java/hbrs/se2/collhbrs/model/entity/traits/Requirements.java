package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "anforderungen", schema = "public")
@IdClass(RequirementsID.class)
public class Requirements extends BaseTraits implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "StellenausschreibungID", nullable = false)
    private Vacancy vacancy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "laufende_nummer", length = 2, nullable = false)
    private int serialNumber;

    @Basic
    @Column(name = "anforderungen", length = 128, nullable = false)
    private String requirementsName;

    @Override
    public RequirementsID getId() {
        RequirementsID id = new RequirementsID();
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