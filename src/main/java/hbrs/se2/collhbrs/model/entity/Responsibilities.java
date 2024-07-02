package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "aufgaben", schema = "public")
@IdClass(ResponsibilitiesID.class)
public class Responsibilities implements Serializable {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Responsibilities responsibilities = (Responsibilities) o;
        return serialNumber == responsibilities.serialNumber &&
                vacancy.equals(responsibilities.vacancy) &&
                responsibilitiesName.equals(responsibilities.responsibilitiesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancy, serialNumber, responsibilitiesName);
    }
}
