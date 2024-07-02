package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "kontaktverknuepfung", schema = "public")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kontaktverknuepfungid", length = 64, nullable = false)
    private long ratingID;
    @ManyToOne
    @JoinColumn(name = "unternehmenid", nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "studentid", nullable = false)
    private Student student;
    @Basic
    @Column(name = "bewertungsscore_student", length = 1)
    private int ratingScoreStudent;
    @Basic
    @Column(name = "beschreibung_student", length = 256)
    private String ratingDescriptionStudent;
    @Basic
    @Column(name = "bewertungsscore_unternehmen", length = 1)
    private int ratingScoreBusiness;
    @Basic
    @Column(name = "beschreibung_unternehmen", length = 256)
    private String ratingDescriptionBusiness;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return ratingID == rating.ratingID &&
                ratingScoreStudent == rating.ratingScoreStudent &&
                ratingScoreBusiness == rating.ratingScoreBusiness &&
                Objects.equals(business, rating.business) &&
                Objects.equals(student, rating.student) &&
                Objects.equals(ratingDescriptionStudent, rating.ratingDescriptionStudent) &&
                Objects.equals(ratingDescriptionBusiness, rating.ratingDescriptionBusiness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingID, business, student, ratingScoreStudent, ratingDescriptionStudent, ratingScoreBusiness, ratingDescriptionBusiness);
    }
}
