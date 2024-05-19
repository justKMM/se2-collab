package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "profil", schema = "public")
public class Profil {
    private long profilID;
    private String avatarUrl;
    private String profilBeschreibung;
    private String xingBenutzername;
    private String linkedinBenutzername;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profilid", nullable = false)
    public long getProfilID() {
        return profilID;
    }

    public void setProfilID(long profilID) {
        this.profilID = profilID;
    }

    @Basic
    @Column(name = "avatar_url", length = 2048)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "profilbeschreibung", length = 6400)
    public String getProfilBeschreibung() {
        return profilBeschreibung;
    }

    public void setProfilBeschreibung(String profilBeschreibung) {
        this.profilBeschreibung = profilBeschreibung;
    }

    @Basic
    @Column(name = "xing_benutzername", length = 2048, unique = true)
    public String getXingBenutzername() {
        return xingBenutzername;
    }

    public void setXingBenutzername(String xingBenutzername) {
        this.xingBenutzername = xingBenutzername;
    }

    @Basic
    @Column(name = "linkedin_benutzername", length = 2048, unique = true)
    public String getLinkedinBenutzername() {
        return linkedinBenutzername;
    }

    public void setLinkedinBenutzername(String linkedinBenutzername) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profil profil = (Profil) o;
        return profilID == profil.profilID &&
                Objects.equals(avatarUrl, profil.avatarUrl) &&
                Objects.equals(profilBeschreibung, profil.profilBeschreibung) &&
                Objects.equals(xingBenutzername, profil.xingBenutzername) &&
                Objects.equals(linkedinBenutzername, profil.linkedinBenutzername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profilID, avatarUrl, profilBeschreibung, xingBenutzername, linkedinBenutzername);
    }
}
