package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Responsibilities;
import hbrs.se2.collhbrs.model.entity.Vacancy;

public class ResponsibilitiesDTO {

    private Vacancy vacancy;
    private int serialNumber;
    private String requirementsName;

    public ResponsibilitiesDTO(Responsibilities responsibilities) {
        this.vacancy = responsibilities.getVacancy();
        this.serialNumber = responsibilities.getSerialNumber();
        this.requirementsName = responsibilities.getResponsibilitiesName();
    }

    public Responsibilities getResponsibilities() {
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(serialNumber);
        responsibilities.setResponsibilitiesName(requirementsName);
        return responsibilities;
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
