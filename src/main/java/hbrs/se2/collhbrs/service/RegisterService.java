package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.repository.*;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
public class RegisterService {

    private final EntityFactory entityFactory;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final StudentRepository studentRepository;
    private final FirstNameRepository firstNameRepository;
    private final BusinessRepository businessRepository;

    public RegisterService(EntityFactory entityFactory, UserRepository userRepository, ProfileRepository profileRepository, StudentRepository studentRepository, FirstNameRepository firstNameRepository, BusinessRepository businessRepository) {
        this.entityFactory = entityFactory;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.studentRepository = studentRepository;
        this.firstNameRepository = firstNameRepository;
        this.businessRepository = businessRepository;
    }

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
            throw new UsernameAlreadyTakenException("Username already taken");
        }

        if (!getUsers().stream().noneMatch(user -> Objects.equals(user.getEmail(), email))) {
            throw new EmailAlreadyTakenException("Email already taken");
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
        for (String firstName : firstNames) {
            saveVorname(entityFactory.createFirstName(firstName, student));
        }
    }

    @Transactional
    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    public Boolean isEmpty() {
        return userRepository.count() == 0;
    }

    public static class UsernameAlreadyTakenException extends RuntimeException {
        public UsernameAlreadyTakenException(String message) {
            super(message);
        }
    }

    public static class EmailAlreadyTakenException extends RuntimeException {
        public EmailAlreadyTakenException(String message) {
            super(message);
        }
    }
}
