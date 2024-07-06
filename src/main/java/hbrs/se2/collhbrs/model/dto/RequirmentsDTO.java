package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RequirmentsDTO implements Serializable {

    private Vacancy vacancy;
    private int serialNumber;
    private String requirementsName;

    public RequirmentsDTO(Requirements requirements) {
        this.vacancy = requirements.getVacancy();
        this.serialNumber = requirements.getSerialNumber();
        this.requirementsName = requirements.getRequirementsName();
    }

    public Requirements getRequirements() {
        Requirements requirements = new Requirements();
        requirements.setVacancy(vacancy);
        requirements.setSerialNumber(serialNumber);
        requirements.setRequirementsName(requirementsName);
        return requirements;
    }

}
