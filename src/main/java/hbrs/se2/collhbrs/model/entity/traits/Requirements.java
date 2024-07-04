package hbrs.se2.collhbrs.model.entity.traits;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "anforderungen", schema = "public")
@IdClass(RequirementsID.class)
public class Requirements extends BaseEntityTraits<Vacancy, RequirementsID> {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "stellenausschreibungID", nullable = false)
    private Vacancy vacancy;

    @Basic
    @Column(name = "anforderungen", length = 128, nullable = false)
    private String requirementsName;

    @Override
    public RequirementsID getId() {
        RequirementsID id = new RequirementsID();
        id.setVacancy(vacancy);
        id.setSerialNumber(super.getSerialNumber());
        return id;
    }
}