package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Email;
import hbrs.se2.collhbrs.model.entity.ResetPasswordToken;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.PasswordTokenRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ResetPasswordService {

    private final UserRepository userRepository;

    private final PasswordTokenRepository passwordTokenRepository;

    private final EmailService emailService;

    public ResetPasswordService(UserRepository userRepository, PasswordTokenRepository passwordTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.emailService = emailService;
    }

    public String sendResetPasswordMail(String usermail) {
        return userExists(usermail) ? emailService.sendSimpleMail(
                createResetPasswordEmail(
                        Globals.BASE_URL,
                        createPasswordResetTokenForUser(userRepository.findByEmail(usermail)),
                        userRepository.findByEmail(usermail)
                )
        ) : "User does not exist, please create a new account.";
    }

    private boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private Email createResetPasswordEmail(String contextPath, String token, User user) {
        return new Email(
                user.getEmail(),
                "Hi " +
                        user.getUsername() +
                        ", click here to reset password:\n" +
                        contextPath + "/" +
                        Globals.Pages.RESET_PASSWORD +
                        "?token=" +
                        token +
                        "\n\n\nAldaringhausen Klangkreationen GmbH\n[Impressum]",
                "collhbrs - Reset Your Password",
                ""
        );
    }

    private String createPasswordResetTokenForUser(User user) {
        return passwordTokenRepository.save(
                new ResetPasswordToken(
                        UUID.randomUUID().toString(),
                        user)
        ).getToken();
    }

    public String resetPassword(String token, String password) {
        String tokenValidity = validatePasswordResetToken(token);
        if (tokenValidity != null) {
            return "Error: " + tokenValidity;
        }

        try {
            updateUserPassword(getUserFromToken(token), password);
            deleteToken(token);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return "Password reset successful";
    }

    private User getUserFromToken(String token) {
        return passwordTokenRepository.findByToken(token).getUser();
    }

    private void updateUserPassword(User user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }

    private void deleteToken(String token) {
        passwordTokenRepository.delete(passwordTokenRepository.findByToken(token));
    }

    protected String validatePasswordResetToken(String token) {
        if (!isTokenFound(passwordTokenRepository.findByToken(token))) {
            return "invalidToken";
        }
        if (isTokenExpired(passwordTokenRepository.findByToken(token))) {
            return "expiredToken";
        }
        return null;
    }

    private boolean isTokenFound(ResetPasswordToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(ResetPasswordToken passToken) {
        return LocalDate.now().isAfter(passToken.getExpireDate());
    }
}
