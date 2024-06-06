package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reset-password-token", schema = "public")
public class ResetPasswordToken {
    private Long tokenid;
    private User user;
    private String token;
    // Token ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenid", nullable = false)
    public Long getTokenID() {
        return tokenid;
    };
    public void setTokenID(Long tokenid) {
        this.tokenid = tokenid;
    }
    // Constructor
    public ResetPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", unique = true)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    // The token itself
    @Basic
    @Column(nullable = false)
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    // Validity duration of token
    @Getter
    @Setter
    @Basic
    @Column(nullable = false)
    public LocalDate EXPIRE_DATE = LocalDate.now().plusDays(1);

    // Disallow empty constructor
    protected ResetPasswordToken() {}
}


