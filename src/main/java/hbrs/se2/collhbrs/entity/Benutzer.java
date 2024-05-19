package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "benutzer", schema = "public")
public class Benutzer {
    private long benutzerID;
    private Profil profil;
    private String username;
    private String passwort;
    private int blacklisted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benutzerid", nullable = false)
    public long getBenutzerID() {
        return benutzerID;
    }

    public void setBenutzerID(long benutzerID) {
        this.benutzerID = benutzerID;
    }

    @OneToOne
    @JoinColumn(name = "profilid", nullable = false, unique = true)
    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Column(name = "username", nullable = false, length = 32, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "passwort", nullable = false, length = 64, unique = true)
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Column(name = "blacklisted", nullable = false, length = 1)
    public int getBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(int blacklisted) {
        this.blacklisted = blacklisted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benutzer benutzer)) return false;
        return benutzerID == benutzer.benutzerID
                && blacklisted == benutzer.blacklisted
                && Objects.equals(profil, benutzer.profil)
                && Objects.equals(username, benutzer.username)
                && Objects.equals(passwort, benutzer.passwort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benutzerID, profil, username, passwort, blacklisted);
    }
}
