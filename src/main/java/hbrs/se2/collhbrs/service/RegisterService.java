package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.*;
import hbrs.se2.collhbrs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FirstNameRepository firstNameRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Transactional
    public boolean completeRegistration(User user) {
        String username = user.getPassword();
        String password = user.getPassword();
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        saveUser(user);
        return true;
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void saveProfil(Profile profile) {
        profileRepository.save(profile);
    }

    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void saveVorname(FirstName firstName) {
        firstNameRepository.save(firstName);
    }

    @Transactional
    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }
}
