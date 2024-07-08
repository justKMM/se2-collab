package collhbrs_test;

import hbrs.se2.collhbrs.CollhbrsApplication;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.ProfileRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CollhbrsApplication.class)
class ApplicationTests {

    private static final String TESTING = "testing";
    private static final Logger LOGGER = Logger.getLogger(ApplicationTests.class.getName());

    private final User currentUser = new User();
    private final Student student = new Student();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        currentUser.setUsername("Jakob9");
        currentUser.setPassword("Jakob1234!");
        currentUser.setBlacklisted(0);
        currentUser.setEmail("jakobus@test.de");
        Profile profile = new Profile();
        profile.setProfileDescription(TESTING);
        profile.setAvatar(TESTING);
        profile.setXingUsername("Jakob123");
        profile.setLinkedinUsername("JakobDerBob");
        profileRepository.save(profile);
        currentUser.setProfile(profile);
        // Speichern in der Datenbank
        userRepository.save(currentUser);
        // Erstellen eines temporären Nutzers zum Testen der Datenbank

        student.setUser(currentUser);
        student.setLastName("Müller");
        studentRepository.save(student);
    }

    @Test
    void testUserProfile() {
        Optional<User> wrapper = userRepository.findById(currentUser.getUserID()); //User km
        if (wrapper.isPresent()) {
            User user = wrapper.get();
            LOGGER.info("User: " + user.getUsername());
            Profile profile = user.getProfile();
            assertEquals(TESTING, profile.getAvatar());
            assertEquals(TESTING, profile.getProfileDescription());
            assertEquals("Jakob123", profile.getXingUsername());
            assertEquals("JakobDerBob", profile.getLinkedinUsername());
        }
    }

    @Test
    void testUserDTOAndFindUserWithJPA() {
        UserDTO testDTO = new UserDTO(userRepository.findByUsernameAndPassword(currentUser.getUsername(), currentUser.getPassword()));
        LOGGER.info("User: " + testDTO.getUsername());
        assertEquals("Jakob9", testDTO.getUsername());
        assertEquals(0, testDTO.getBlacklisted());
        assertEquals("jakobus@test.de", testDTO.getEmail());
    }

    @Test
    void testStudentDTOByStudentID() {
        StudentDTO studentDTO = new StudentDTO(studentRepository.findStudentByUserUserID(currentUser.getUserID()));
        LOGGER.info("Student: " + studentDTO.getUser().getUsername());
        assertEquals(currentUser.getUsername(), studentDTO.getUser().getUsername());
        assertEquals(student.getStudentID(), studentDTO.getStudentID());
    }

    @Test
    void testPersonLoad() {
        Optional<User> wrapper = userRepository.findById(currentUser.getUserID());
        if (wrapper.isPresent()) {
            User user = wrapper.get();
            assertEquals("Jakob1234!", user.getPassword());
        }
    }

    @AfterEach
    void teardown() {
        studentRepository.deleteById(student.getStudentID());
        userRepository.deleteById(currentUser.getUserID());
        profileRepository.delete(currentUser.getProfile());
    }
}