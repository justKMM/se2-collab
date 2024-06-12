package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.ResetPasswordToken;
import hbrs.se2.collhbrs.repository.PasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SecurityService {

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    protected String validatePasswordResetToken(String token) {
        final ResetPasswordToken passToken = passwordTokenRepository.findByToken(token);
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expiredToken"
                : null;
    }

    private boolean isTokenFound(ResetPasswordToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(ResetPasswordToken passToken) {
        return LocalDate.now().isAfter(passToken.getEXPIRE_DATE());
    }
}
