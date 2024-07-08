package hbrs.se2.collhbrs.service.registration;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.repository.*;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final EntityFactory entityFactory;

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final StudentRepository studentRepository;

    private final FirstNameRepository firstNameRepository;

    private final BusinessRepository businessRepository;

    public RegisterServiceImpl(EntityFactory entityFactory, UserRepository userRepository, ProfileRepository profileRepository, StudentRepository studentRepository, FirstNameRepository firstNameRepository, BusinessRepository businessRepository) {
        this.entityFactory = entityFactory;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.studentRepository = studentRepository;
        this.firstNameRepository = firstNameRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void registerBusiness(String username, String password, String email, String businessName) {
        saveBusiness(entityFactory.createBusiness(businessName, registerUser(username, password, email)));
    }

    @Override
    public void registerStudent(String username, String password, String email, String firstName, String lastName) {
        Student student = entityFactory.createStudent(registerUser(username, password, email), lastName);
        saveStudent(student);
        saveFirstNames(firstName.split(" "), student);
    }

    private User registerUser(String username, String password, String email) {
        if (getUsers().stream().anyMatch(user -> Objects.equals(user.getUsername(), username))) {
            throw new UsernameAlreadyTakenException("Username already taken");
        }

        if (getUsers().stream().anyMatch(user -> Objects.equals(user.getEmail(), email))) {
            throw new EmailAlreadyTakenException("Email already taken");
        }

        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, username, password, email);

        saveProfile(profile);
        saveUser(user);
        return user;
    }

    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void saveVorname(FirstName firstName) {
        firstNameRepository.save(firstName);
    }

    @Override
    public void saveFirstNames(String[] firstNames, Student student) {
        for (String firstName : firstNames) {
            saveVorname(entityFactory.createFirstName(firstName, student));
        }
    }

    @Override
    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    @Override
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