package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.repository.ProfileRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}