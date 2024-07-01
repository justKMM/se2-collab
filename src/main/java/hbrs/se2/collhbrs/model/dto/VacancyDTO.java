package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;

import java.sql.Date;

public class VacancyDTO {

    private long vacancyID;
    private Business business;
    private String titel;
    private String location;
    private String description;
    private Date publishDate;

    public VacancyDTO(Vacancy entity) {
        this.vacancyID = entity.getVacancyID();
        this.business = entity.getBusiness();
        this.titel = entity.getTitel();
        this.location = entity.getLocation();
        this.publishDate = entity.getPublishDate();
        this.description = entity.getDescription();
    }

    public Vacancy getVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(vacancyID);
        vacancy.setBusiness(business);
        vacancy.setTitel(titel);
        vacancy.setDescription(description);
        return vacancy;
    }

    public long getVacancyID() {
        return vacancyID;
    }

    public void setVacancyID(long vacancyID) {
        this.vacancyID = vacancyID;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
