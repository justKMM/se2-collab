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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business that = (Business) o;
        return businessID == that.businessID &&
                Objects.equals(user, that.user) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessID, user, name);
    }
}
