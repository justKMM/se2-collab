package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "kontaktverknuepfung", schema = "public")
public class Rating {
    private long ratingID;
    private Business business;
    private Student student;
    private int ratingScoreStudent;
    private String ratingDescriptionStudent;
    private int ratingScoreBusiness;
    private String ratingDescriptionBusiness;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kontaktverknuepfungid", length = 64, nullable = false)
    public long getRatingID() {
        return ratingID;
    }

    public void setRatingID(long ratingID) {
        this.ratingID = ratingID;
    }

    @ManyToOne
    @JoinColumn(name = "studentid", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
    @Column(name = "bewertungsscore_student", length = 1)
    public int getRatingScoreStudent() {
        return ratingScoreStudent;
    }

    public void setRatingScoreStudent(int ratingScoreStudent) {
        this.ratingScoreStudent = ratingScoreStudent;
    }

    @Basic
    @Column(name = "beschreibung_student", length = 256)
    public String getRatingDescriptionStudent() {
        return ratingDescriptionStudent;
    }

    public void setRatingDescriptionStudent(String ratingDescriptionStudent) {
        this.ratingDescriptionStudent = ratingDescriptionStudent;
    }

    @Basic
    @Column(name = "bewertungsscore_unternehmen", length = 1)
    public int getRatingScoreBusiness() {
        return ratingScoreBusiness;
    }

    public void setRatingScoreBusiness(int ratingScoreBusiness) {
        this.ratingScoreBusiness = ratingScoreBusiness;
    }

    @Basic
    @Column(name = "beschreibung_unternehmen", length = 256)
    public String getRatingDescriptionBusiness() {
        return ratingDescriptionBusiness;
    }

    public void setRatingDescriptionBusiness(String ratingDescriptionBusiness) {
        this.ratingDescriptionBusiness = ratingDescriptionBusiness;
    }

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
