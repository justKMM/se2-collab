package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;

public class VacancyDTO {
    private long vacancyID;
    private Business business;
    private String titel;
    private String description;

    public VacancyDTO(Vacancy entity) {
        this.vacancyID = entity.getVacancyID();
        this.business = entity.getBusiness();
        this.titel = entity.getTitel();
        this.description = entity.getDescription();
    }

    public Vacancy getVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(vacancyID);
        vacancy.setBusiness(business);
        vacancy.setTitel(titel);
        vacancy.setDescription(description);
        return  vacancy;
    }

    public long getVacancyID() {
        return vacancyID;
    }

    public Business getBusiness() {
        return business;
    }

    public String getTitel() {
        return titel;
    }

    public String getDescription() {
        return description;
    }

    public void setVacancyID(long vacancyID) {
        this.vacancyID = vacancyID;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
