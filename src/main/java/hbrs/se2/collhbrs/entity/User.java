package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "benutzer", schema = "public")
public class User {
    private long userID;
    private Profile profile;
    private String username;
    private String password;
    private int blacklisted;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benutzerid", nullable = false)
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    @OneToOne
    @JoinColumn(name = "profilid", nullable = false, unique = true)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Column(name = "username", nullable = false, length = 32, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "passwort", nullable = false, length = 64, unique = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "blacklisted", nullable = false, length = 1)
    public int getBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(int blacklisted) {
        this.blacklisted = blacklisted;
    }

    @Column(name = "email", nullable = false, length = 320, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID &&
                blacklisted == user.blacklisted &&
                Objects.equals(profile, user.profile) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    public int hashCode() {
        return Objects.hash(userID, profile, username, password, blacklisted);
    }
}
