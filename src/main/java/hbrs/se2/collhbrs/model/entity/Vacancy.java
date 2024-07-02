package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "stellenausschreibung", schema = "public")
public class Vacancy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stellenausschreibungid", length = 64, nullable = false)
    private long vacancyID;
    @ManyToOne
    @JoinColumn(name = "unternehmenid", nullable = false)
    private Business business;
    @Basic
    @Column(name = "titel", length = 256, nullable = false)
    private String titel;
    @Basic
    @Column(name = "beschreibung", length = 6400, nullable = false)
    private String description;
    @Basic
    @Column(name = "anstellungsart", length = 128, nullable = false)
    private String employmentType;
    @Basic
    @Column(name = "standort", length = 256, nullable = false)
    private String location;
    @Basic
    @Column(name = "veroeffentlichungsdatum", nullable = false)
    private Date publishDate;


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
