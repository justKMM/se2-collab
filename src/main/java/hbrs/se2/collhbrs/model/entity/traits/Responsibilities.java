package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aufgaben", schema = "public")
@IdClass(ResponsibilitiesID.class)
public class Responsibilities extends BaseEntityTraits<Vacancy, ResponsibilitiesID> {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "stellenausschreibungID", nullable = false)
    private Vacancy vacancy;

    @Basic
    @Column(name = "aufgaben", length = 2400, nullable = false)
    private String responsibilitiesName;

    @Override
    public ResponsibilitiesID getId() {
        ResponsibilitiesID id = new ResponsibilitiesID();
        id.setVacancy(vacancy);
        id.setSerialNumber(super.getSerialNumber());
        return id;
    }

    @Override
    public Vacancy getEntity() {
        return vacancy;
    }
}