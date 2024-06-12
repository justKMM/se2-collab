package hbrs.se2.collhbrs.model.entity;

import hbrs.se2.collhbrs.util.Globals;
import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

import static java.util.Collections.emptyList;

@Entity
@Table(name = "benutzer", schema = "public")
public class User implements Serializable, UserDetails {
    private long userID;
    private Profile profile;
    private String username;
    private String password;
    private int blacklisted;
    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "authorities", targetEntity = Authority.class)
    @Convert(converter = AuthorityListConverter.class)
    @Setter
    private List<Authority> authorities = new ArrayList<>() {
        {
            add(new Authority(Globals.Roles.USER));
        }
    };

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

    //
    @Setter
    private boolean accountNonExpired = true;
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    @Setter
    private boolean accountNonLocked = true;
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @Setter
    private boolean credentialsNonExpired = true;
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @Setter
    private boolean enabled = true;
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Converter
    public class AuthorityListConverter implements AttributeConverter<List<Authority>, String> {
        private static final String SPLIT_CHAR = ";";
        private String toString(List<Authority> authorities) {
            StringBuilder result = new StringBuilder();
            for (Authority authority : authorities) {
                result.append(authority.toString()).append(SPLIT_CHAR);
            }
            return result.toString();
        }
        private List<Authority> toList(String string) {
            List<Authority> result = new ArrayList<>();
            for (String s : string.split(SPLIT_CHAR)) {
                result.add(new Authority(s));
            }
            return result;
        }
        @Override
        public String convertToDatabaseColumn(List<Authority> authorities) {
            return authorities != null ? toString(authorities) : null;
        }

        @Override
        public List<Authority> convertToEntityAttribute(String string) {
            return string != null ? toList(string) : emptyList();
        }
    }
}
