package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "stellenausschreibung", schema = "public")
public class Vacancy implements Serializable {
    private long vacancyID;
    private Business business;
    private String titel;
    private String description;
    private String employmentType;
    private String location;
    private Date publishDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stellenausschreibungid", length = 64, nullable = false)
    public long getVacancyID() {
        return vacancyID;
    }

    public void setVacancyID(long vacancyID) {
        this.vacancyID = vacancyID;
    }

    @ManyToOne
    @JoinColumn(name = "unternehmenid", nullable = false)
    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Basic
    @Column(name = "titel", length = 256, nullable = false)
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Basic
    @Column(name = "beschreibung", length = 6400, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "standort", length = 256, nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "veroeffentlichungsdatum", nullable = false)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "anstellungsart", length = 128, nullable = false)
    public String getEmploymentType() {
        return this.employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return vacancyID == vacancy.vacancyID &&
                Objects.equals(business, vacancy.business) &&
                Objects.equals(titel, vacancy.titel) &&
                Objects.equals(description, vacancy.description) &&
                Objects.equals(location, vacancy.location) &&
                Objects.equals(publishDate, vacancy.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyID, business, titel, description, location, publishDate);
    }

}
