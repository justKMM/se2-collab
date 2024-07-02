package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "profil", schema = "public")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profilid", nullable = false)
    private long profileID;
    @Basic
    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;
    @Basic
    @Column(name = "profilbeschreibung", length = 6400)
    private String profileDescription;
    @Basic
    @Column(name = "xing_benutzername", length = 2048, unique = true)
    private String xingUsername;
    @Basic
    @Column(name = "linkedin_benutzername", length = 2048, unique = true)
    private String linkedinUsername;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return profileID == profile.profileID &&
                Objects.equals(avatar, profile.avatar) &&
                Objects.equals(profileDescription, profile.profileDescription) &&
                Objects.equals(xingUsername, profile.xingUsername) &&
                Objects.equals(linkedinUsername, profile.linkedinUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID, avatar, profileDescription, xingUsername, linkedinUsername);
    }
}
