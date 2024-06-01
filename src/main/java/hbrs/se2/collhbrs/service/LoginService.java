package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.service.db.exceptions.DatabaseLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private UserDTO userDTO = null;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) throws DatabaseLayerException {
        UserDTO tmpUser = this.getUser(username, password);

        if (tmpUser == null) {
            return false;
        }
        this.userDTO = tmpUser;
        return true;
    }

    public boolean isBlacklisted(String username, String password) throws DatabaseLayerException {
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

    private UserDTO getUser(String username, String password) throws DatabaseLayerException {
        UserDTO userTmp;

        try {
            userTmp = userRepository.findUserByUsernameAndPassword(username, password);
        } catch (org.springframework.dao.DataAccessResourceFailureException e) {
            throw new DatabaseLayerException("User mit diesem Username und/oder Passwort konnte nicht gefunden werden.");
        }
        return userTmp;
    }

}
