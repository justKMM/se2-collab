package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.repository.*;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class RegisterService {

    @Autowired
    private EntityFactory entityFactory;

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
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void registerBusiness(String username, String password, String email, String businessName) {
        saveBusiness(entityFactory.createBusiness(businessName, registerUser(username, password, email)));
    }

    @Transactional
    public void registerStudent(String username, String password, String email, String firstName, String lastName) {
        Student student = entityFactory.createStudent(registerUser(username, password, email), lastName);
        saveStudent(student);
        saveFirstNames(firstName.split(" "), student);
    }

    private User registerUser(String username, String password, String email) {
        if (!getUsers().stream().noneMatch(user -> Objects.equals(user.getUsername(), username))) {
            throw new RuntimeException("Username already taken");
        }

        if (!getUsers().stream().noneMatch(user -> Objects.equals(user.getEmail(), email))) {
            throw new RuntimeException("Email already taken");
        }

        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, username, password, email);

        saveProfile(profile);
        saveUser(user);
        return user;
    }

    @Transactional
    public void saveProfile(Profile profile) {
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
    public void saveFirstNames(String[] firstNames, Student student) {
        IntStream.range(0, firstNames.length).forEach(counter -> {
            saveVorname(entityFactory.createFirstName(firstNames[counter], student, counter));
        });
    }

    @Transactional
    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    public Boolean isEmpty() {
        return userRepository.count() == 0;
    }
}
