package hbrs.se2.collhbrsTest;

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

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = CollhbrsApplication.class)
class ApplicationTests {

    private final User user = new User();
    private final Student student = new Student();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        user.setUsername("Jakob9");
        user.setPassword("Jakob1234!");
        user.setBlacklisted(0);
        user.setEmail("jakobus@test.de");
        Profile profile = new Profile();
        profile.setProfileDescription("testing");
        profile.setAvatar("testing");
        profile.setXingUsername("Jakob123");
        profile.setLinkedinUsername("JakobDerBob");
        profileRepository.save(profile);
        user.setProfile(profile);
        // Speichern in der Datenbank
        userRepository.save(user);
        //Erstellen eines temporären Nutzers zum Testen der Datenbank

        student.setUser(user);
        student.setLastName("Müller");
        studentRepository.save(student);
    }

    @Test
    void testUserProfile() {
        Optional<User> wrapper = userRepository.findById(user.getUserID()); //User km
        if (wrapper.isPresent()) {
            User user = wrapper.get();
            System.out.println("User: " + user.getUsername());
            Profile profile = user.getProfile();
            assertEquals("testing", profile.getAvatar());
            assertEquals("testing", profile.getProfileDescription());
            assertEquals("Jakob123", profile.getXingUsername());
            assertEquals("JakobDerBob", profile.getLinkedinUsername());
        }
    }

    @Test
    void testUserDTOAndFindUserWithJPA() {
        UserDTO testDTO = new UserDTO(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
        System.out.println("User: " + testDTO.getUsername());
        assertEquals("Jakob9", testDTO.getUsername());
        assertEquals(0, testDTO.getBlacklisted());
        assertEquals("jakobus@test.de", testDTO.getEmail());
    }

    @Test
    void testStudentDTOByStudentID() {
        StudentDTO studentDTO = new StudentDTO(studentRepository.findStudentByUserUserID(user.getUserID()));
        System.out.println("Student: " + studentDTO.getUser().getUsername());
        assertEquals(user.getUsername(), studentDTO.getUser().getUsername());
        assertEquals(student.getStudentID(), studentDTO.getStudentID());
    }

    @Test
    void testPersonLoad() {
        Optional<User> wrapper = userRepository.findById(user.getUserID());
        if (wrapper.isPresent()) {
            User user = wrapper.get();
            assertEquals("Jakob1234!", user.getPassword());
        }
    }

    @AfterEach
    void teardown() {
        studentRepository.deleteById(student.getStudentID());
        userRepository.deleteById(user.getUserID());
        profileRepository.delete(user.getProfile());
    }
}
