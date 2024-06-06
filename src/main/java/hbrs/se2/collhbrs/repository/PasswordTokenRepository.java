package hbrs.se2.collhbrs.repository;

import hbrs.se2.collhbrs.model.entity.ResetPasswordToken;
import hbrs.se2.collhbrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);
}
