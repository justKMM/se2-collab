package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "logindaten", schema = "public")
public class LoginDaten {
    private Benutzer benutzer;
    private String passwort;


    @Id
    @OneToOne
    @JoinColumn(name = "username", nullable = false)
    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    // TODO: Passwort nicht im Klartext ablegen

    @Basic
    @Column(name = "passwort", length = 64, nullable = false)
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDaten loginDaten = (LoginDaten) o;
        return Objects.equals(benutzer, loginDaten.benutzer) &&
                Objects.equals(passwort, loginDaten.passwort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benutzer, passwort);
    }
}
