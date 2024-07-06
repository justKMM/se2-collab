package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ResponsibilitiesDTO implements Serializable {

    private Vacancy vacancy;
    private int serialNumber;
    private String responsibilitiesName;

    public ResponsibilitiesDTO(Responsibilities responsibilities) {
        this.vacancy = responsibilities.getVacancy();
        this.serialNumber = responsibilities.getSerialNumber();
        this.responsibilitiesName = responsibilities.getResponsibilitiesName();
    }

    public Responsibilities getResponsibilities() {
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(serialNumber);
        responsibilities.setResponsibilitiesName(responsibilitiesName);
        return responsibilities;
    }

}
