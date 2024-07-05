package DTOsTest;

import hbrs.se2.collhbrs.model.dto.InterestDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.model.entity.traits.Interest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InterestDTOTest {

    @Mock
    StudentDTO studentDTO;
    private InterestDTO interestDTO;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUserID(1L);
        user.setUsername("testuser");
        Student student = new Student();
        student.setUser(user);
        Interest interest = new Interest();
        interest.setStudent(student);
        interestDTO = new InterestDTO(interest);
        studentDTO = new StudentDTO(student);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, interestDTO.getSerialNumber());
        assertNull(interestDTO.getInterestName());

        interestDTO.setSerialNumber(123);
        interestDTO.setInterestName("Testing");

        assertEquals(studentDTO.getStudent(), interestDTO.getStudent());
        assertEquals(123, interestDTO.getSerialNumber());
        assertEquals("Testing", interestDTO.getInterestName());
    }
}
