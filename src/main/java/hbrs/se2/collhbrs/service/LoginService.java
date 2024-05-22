package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User getUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }
}
