package hbrs.se2.collhbrs.service;

import com.vaadin.flow.component.UI;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BusinessRepository businessRepository;


    public void startSession(UserDTO user) {
        if (isUserStudent(user) && !isBlacklisted(user)) {
            VaadinSession.getCurrent().setAttribute(
                    Globals.CURRENT_USER,
                    new StudentDTO(studentRepository.findStudentByUser_UserID(user.getUserID())));
            Notification.show("Student logged in successfully");
            UI.getCurrent().navigate(Globals.Pages.MAIN);
        } else if (isUserBusiness(user) && !isBlacklisted(user)) {
            VaadinSession.getCurrent().setAttribute(
                    Globals.CURRENT_USER,
                    new BusinessDTO(businessRepository.findBusinessByUser_UserID(user.getUserID())));
            Notification.show("Company logged in successfully");
            UI.getCurrent().navigate(Globals.Pages.MAIN);
        } else {
            Notification.show("Account is Blacklisted!");
        }
    }

    public boolean isUserStudent(UserDTO user) {
        return studentRepository.existsByUser_UserID(user.getUserID());
    }

    public boolean isUserBusiness(UserDTO user) {
        return businessRepository.existsByUser_UserID(user.getUserID());
    }

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public boolean isBlacklisted(UserDTO user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).getBlacklisted() == 1;
    }
}
