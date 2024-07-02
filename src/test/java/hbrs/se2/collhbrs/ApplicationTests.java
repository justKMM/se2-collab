package hbrs.se2.collhbrs;

import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.FirstNameRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ApplicationTests {

    private final static String HUENCHEN = "huenchen";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FirstNameRepository firstNameRepository;

    @Test
    void testUserProfile() {
        Optional<User> wrapper = userRepository.findById((long) 4); //User km
        if (wrapper.isPresent()) {
            User user = wrapper.get();
            System.out.println("User: " + user.getUsername());
            Profile profile = user.getProfile();
            assertEquals("dsakldaskldaskldaskldsalk", profile.getAvatar());
            assertEquals("adsladskldasladskldaslkdsakl", profile.getProfileDescription());
            assertEquals("Loool", profile.getXingUsername());
            assertEquals("Laaal", profile.getLinkedinUsername());
        }
    }

    @Test
    void testUserDTOAndFindUserWithJPA() {
        UserDTO testDTO = new UserDTO(userRepository.findByUsernameAndPassword(HUENCHEN, "hund")); //User huenchen
        System.out.println("User: " + testDTO.getUsername());
        assertEquals(HUENCHEN, testDTO.getUsername());
        assertEquals(0, testDTO.getBlacklisted());
        assertEquals("huhn@huhn.de", testDTO.getEmail());
    }

    @Test
    void testStudentDTOByStudentID() {
        StudentDTO studentDTO = new StudentDTO(studentRepository.findStudentByUserUserID((long) 1)); //User huenchen
        System.out.println("Student: " + studentDTO.getUser().getUsername());
        assertEquals(HUENCHEN, studentDTO.getUser().getUsername());
        assertEquals(1, studentDTO.getStudentID());
    }

    @Test
    void testPersonLoad() {
        Optional<User> wrapper = userRepository.findById((long) 10); //User km1
        if (wrapper.isPresent()) {
            User user = wrapper.get();
            assertEquals("km", user.getPassword());
        }
    }

    @Test
    void testFindStudentByFirstName() {
        Student student = studentRepository.findStudentByUserUserID((long) 1);
        assertEquals(student.getStudentID(), firstNameRepository.findAll().get(0).getStudent().getStudentID());
        assertEquals(student.getStudentID(), firstNameRepository.findAll().get(1).getStudent().getStudentID());
        assertEquals(student.getStudentID(), firstNameRepository.findAll().get(2).getStudent().getStudentID());
    }

    //TO-DO: Komplexere Testfälle testen
}
