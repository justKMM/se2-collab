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
