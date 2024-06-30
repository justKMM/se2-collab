package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "anforderungen", schema = "public")
@IdClass(RequirementsID.class)
public class Requirements implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    private Vacancy vacancy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serialNumber;

    @Basic
    private String requirementsName;

    @JoinColumn(name = "StellenausschreibungID", nullable = false)
    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Column(name = "laufende_nummer", length = 2, nullable = false)
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(name = "anforderungen", length = 128, nullable = false)
    public String getRequirementsName() {
        return requirementsName;
    }

    public void setRequirementsName(String requirementsName) {
        this.requirementsName = requirementsName;
    }

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
