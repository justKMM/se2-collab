package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "benutzer", schema = "public")
public class Benutzer {
    private long benutzerID;
    private Profil profil;
    private String username;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benutzer benutzer = (Benutzer) o;
        return benutzerID == benutzer.benutzerID &&
                Objects.equals(profil, benutzer.profil) &&
                Objects.equals(username, benutzer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benutzerID, profil, username);
    }
}
