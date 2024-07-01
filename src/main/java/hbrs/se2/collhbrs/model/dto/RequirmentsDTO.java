package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Requirements;
import hbrs.se2.collhbrs.model.entity.Vacancy;

public class RequirmentsDTO {

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

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRequirementsName() {
        return requirementsName;
    }

    public void setRequirementsName(String requirementsName) {
        this.requirementsName = requirementsName;
    }
}
