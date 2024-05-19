package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "unternehmen", schema = "public")
public class Unternehmen {
    private long unternehmenID;
    private Benutzer benutzer;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unternehmenid", length = 64, nullable = false)
    public long getUnternehmenID() {
        return unternehmenID;
    }

    public void setUnternehmenID(long unternehmenID) {
        this.unternehmenID = unternehmenID;
    }

    @OneToOne
    @JoinColumn(name = "benutzerid", nullable = false, unique = true)
    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    @Basic
    @Column(name = "name", length = 128, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unternehmen that = (Unternehmen) o;
        return unternehmenID == that.unternehmenID &&
                Objects.equals(benutzer, that.benutzer) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unternehmenID, benutzer, name);
    }
}
