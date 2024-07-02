package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
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

}
