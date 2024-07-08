package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "unternehmen", schema = "public")
public class Business implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unternehmenid", length = 64, nullable = false)
    private long businessID;

    @OneToOne
    @JoinColumn(name = "benutzerid", nullable = false, unique = true)
    private User user;

    @Basic
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Basic
    @Column(name = "adresse", length = 128)
    private String address;

    @Basic
    @Column(name = "stadt", length = 128)
    private String city;

    @Basic
    @Column(name = "postleitzahl", length = 8)
    private String zipCode;

    @Basic
    @Column(name = "land", length = 128)
    private String country;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return businessID == business.businessID &&
                Objects.equals(user, business.user) &&
                Objects.equals(name, business.name) &&
                Objects.equals(address, business.address) &&
                Objects.equals(city, business.city) &&
                Objects.equals(zipCode, business.zipCode) &&
                Objects.equals(country, business.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessID, user, name, address, city, zipCode, country);
    }
}
