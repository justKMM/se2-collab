package hbrs.se2.collhbrs.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "passwortresettoken", schema = "public")
public class ResetPasswordToken {

    @Basic
    @Column(name = "ablaufdatum", nullable = false)
    public LocalDate expireDate = LocalDate.now().plusDays(1);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passwortresettokenid", nullable = false)
    private Long tokenID;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "benutzerid", unique = true)
    private User user;
    @Basic
    @Column(name = "token", nullable = false)
    private String token;

    public ResetPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    protected ResetPasswordToken() {
    }
}


