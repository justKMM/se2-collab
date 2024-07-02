package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "anforderungen", schema = "public")
@IdClass(RequirementsID.class)
public class Requirements implements Serializable {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requirements requirements = (Requirements) o;
        return serialNumber == requirements.serialNumber &&
                vacancy.equals(requirements.vacancy) &&
                requirementsName.equals(requirements.requirementsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancy, serialNumber, requirementsName);
    }
}
