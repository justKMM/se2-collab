package hbrs.se2.collhbrs.service;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final BusinessRepository businessRepository;

    public LoginService(UserRepository userRepository, StudentRepository studentRepository, BusinessRepository businessRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.businessRepository = businessRepository;
    }

    public void startSession(UserDTO user) {
        if (isUserStudent(user) && !isBlacklisted(user)) {
            startStudentSession(user);
        } else if (isUserBusiness(user) && !isBlacklisted(user)) {
            startBusinessSession(user);
        } else {
            Notification.show("Ihr Account ist gebannt! Bitte kontaktieren Sie den Administrator.");
        }
    }

    private void startBusinessSession(UserDTO user) {
        VaadinSession.getCurrent().setAttribute(
                Globals.CURRENT_USER,
                new BusinessDTO(businessRepository.findBusinessByUserUserID(user.getUserID())));
        Notification.show("Erfolgreich eingeloggt!");
    }

    private void startStudentSession(UserDTO user) {
        VaadinSession.getCurrent().setAttribute(
                Globals.CURRENT_USER,
                new StudentDTO(studentRepository.findStudentByUserUserID(user.getUserID())));
        Notification.show("Erfolgreich eingeloggt!");
    }

    private boolean isUserStudent(UserDTO user) {
        return studentRepository.existsByUserUserID(user.getUserID());
    }

    private boolean isUserBusiness(UserDTO user) {
        return businessRepository.existsByUserUserID(user.getUserID());
    }

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public boolean isBlacklisted(UserDTO user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).getBlacklisted() == 1;
    }

    public User getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return rawPassword.equals(user.getPassword());
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
