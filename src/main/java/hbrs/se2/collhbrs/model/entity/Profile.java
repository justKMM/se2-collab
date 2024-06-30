package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "profil", schema = "public")
public class Profile implements Serializable {
    private long profileID;
    private String avatarUrl;
    private String profileDescription;
    private String xingUsername;
    private String linkedinUsername;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profilid", nullable = false)
    public long getProfileID() {
        return profileID;
    }

    public void setProfileID(long profileID) {
        this.profileID = profileID;
    }

    @Basic
    @Column(name = "avatar", columnDefinition = "TEXT")
    @Lob
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "profilbeschreibung", length = 6400)
    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Basic
    @Column(name = "xing_benutzername", length = 2048, unique = true)
    public String getXingUsername() {
        return xingUsername;
    }

    public void setXingUsername(String xingUsername) {
        this.xingUsername = xingUsername;
    }

    @Basic
    @Column(name = "linkedin_benutzername", length = 2048, unique = true)
    public String getLinkedinUsername() {
        return linkedinUsername;
    }

    public void setLinkedinUsername(String linkedinUsername) {
        this.linkedinUsername = linkedinUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return profileID == profile.profileID &&
                Objects.equals(avatarUrl, profile.avatarUrl) &&
                Objects.equals(profileDescription, profile.profileDescription) &&
                Objects.equals(xingUsername, profile.xingUsername) &&
                Objects.equals(linkedinUsername, profile.linkedinUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID, avatarUrl, profileDescription, xingUsername, linkedinUsername);
    }
}
