package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "passwortresettoken", schema = "public")
public class ResetPasswordToken {
    @Getter
    @Setter
    @Basic
    @Column(name = "ablaufdatum", nullable = false)
    public LocalDate expireDate = LocalDate.now().plusDays(1);
    private Long tokenid;
    private User user;
    private String token;

    public ResetPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    protected ResetPasswordToken() {
    }

    // Token ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passwortresettokenid", nullable = false)

    public Long getTokenID() {
        return tokenid;
    }

    public void setTokenID(Long tokenid) {
        this.tokenid = tokenid;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "benutzerid", unique = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "token", nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


