package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Responsibilities;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponsibilitiesDTO {

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
