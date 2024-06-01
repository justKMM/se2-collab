package hbrs.se2.collhbrs.control;

import hbrs.se2.collhbrs.control.exception.DatabaseUserException;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginControl {
    private final UserRepository userRepository;
    private UserDTO userDTO = null;

    @Autowired
    public LoginControl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) throws DatabaseUserException {
        UserDTO tmpUser = this.getUser(username, password);

        if (tmpUser == null) {
            return false;
        }
        this.userDTO = tmpUser;
        return true;
    }

    public boolean isBlacklisted(String username, String password) throws DatabaseUserException {
        UserDTO tmpUser = this.getUser(username, password);
        if (tmpUser == null) {
            return true;
        }

        this.userDTO = tmpUser;
        return this.userDTO.getBlacklisted() == 1;
    }

    public UserDTO getCurrentUser() {
        return this.userDTO;
    }

    private UserDTO getUser(String username, String password) throws DatabaseUserException {
        UserDTO userTmp;

        try {
            userTmp = userRepository.findUserByUsernameAndPassword(username, password);
        } catch (org.springframework.dao.DataAccessResourceFailureException e) {
            throw new DatabaseUserException("User mit diesem Username und/oder Passwort konnte nicht gefunden werden.");
        }
        return userTmp;
    }

}
