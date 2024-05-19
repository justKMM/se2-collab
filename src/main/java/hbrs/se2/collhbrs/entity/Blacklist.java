package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "blacklist", schema = "public")
public class Blacklist {
    private Benutzer benutzer;
    private String blacklisted;


    @Id
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }


    @Basic
    @Column(name = "blacklisted", length = 1, nullable = false)
    public String getBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(String blacklisted) {
        this.blacklisted = blacklisted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blacklist blacklist = (Blacklist) o;
        return Objects.equals(benutzer, blacklist.benutzer) &&
                Objects.equals(blacklisted, blacklist.blacklisted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benutzer, blacklisted);
    }
}
