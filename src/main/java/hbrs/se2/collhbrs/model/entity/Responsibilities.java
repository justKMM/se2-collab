package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "aufgaben", schema = "public")
@IdClass(ResponsibilitiesID.class)
public class Responsibilities implements Serializable {

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

    @JoinColumn(name = "StellenausschreibungID", nullable = false)
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }


    @Column(name = "laufende_nummer", length = 2, nullable = false)
    public int getSerialNumber() {
        return serialNumber;
    }

    @Column(name = "aufgaben", length = 128, nullable = false)
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getResponsibilitiesName() {
        return requirementsName;
    }

    public void setResponsibilitiesName(String requirementsName) {
        this.requirementsName = requirementsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Responsibilities responsibilities = (Responsibilities) o;
        return serialNumber == responsibilities.serialNumber &&
                vacancy.equals(responsibilities.vacancy) &&
                requirementsName.equals(responsibilities.requirementsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancy, serialNumber, requirementsName);
    }
}
