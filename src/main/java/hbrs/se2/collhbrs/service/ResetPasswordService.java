package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Email;
import hbrs.se2.collhbrs.model.entity.ResetPasswordToken;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.repository.PasswordTokenRepository;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResetPasswordService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordTokenRepository passwordTokenRepository;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EmailService emailService;

    // Sending the mail
    public String sendResetPasswordMail(String usermail) {
        boolean userExists = checkUserExistence(usermail);
        if (userExists) {
            // Saving the token
            String token = UUID.randomUUID().toString();
            User user = userRepository.findByEmail(usermail);
            createPasswordResetTokenForUser(user, token);
            // Creating the email
            Email email = createResetPasswordEmail(Globals.BASE_URL, token, usermail);
            return emailService.sendSimpleMail(email);
        }
        return "User does not exist, please create a new account.";
    }

    private boolean checkUserExistence(String email) {
        return userRepository.existsByEmail(email);
    }

    private String findUserNameByEmail(String email) {
        return userRepository.findByEmail(email).getUsername();
    }
    // Creating the email
    private Email createResetPasswordEmail(String contextPath, String token, String usermail) {
        String username = findUserNameByEmail(usermail);
        // String components
        String url = contextPath + "/" + Globals.Pages.RESET_PASSWORD + "?token=" + token;
        String messageBegin = "Hi " + username + ", click here to reset password:\n";
        String messageEnd = "\n\n\nAldaringhausen Klangkreationen GmbH\n[Impressum]";
        // Entire message
        String message = messageBegin + url + messageEnd;
        // Creating the email
        return new Email(usermail, message, "collhbrs - Reset Your Password", "");
    }
    // Creating the token
    private void createPasswordResetTokenForUser(User user, String token) {
        ResetPasswordToken myToken = new ResetPasswordToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    public String resetPassword(String token, String password) {
        String tokenValidity = securityService.validatePasswordResetToken(token);
        if (tokenValidity != null) {
            return "Error" + tokenValidity;
        }
        ResetPasswordToken tmpToken = passwordTokenRepository.findByToken(token);
        User user = tmpToken.getUser();
        try {
            user.setPassword(password);
            userRepository.save(user);
            passwordTokenRepository.delete(tmpToken);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return "Password reset successful";
    }
}
