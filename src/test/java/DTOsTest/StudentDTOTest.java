package DTOsTest;

import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentDTOTest {

    @Mock
    UserDTO userDTO;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        studentDTO = new StudentDTO(new Student());
        userDTO = new UserDTO(new User());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, studentDTO.getStudentID());
        assertNull(studentDTO.getUser());
        assertNull(studentDTO.getLastName());
        assertNull(studentDTO.getBirthdate());


        studentDTO.setStudentID(1L);
        studentDTO.setLastName("Meier");
        studentDTO.setBirthdate(LocalDate.of(2011, 1, 11));
        studentDTO.setUser(new User());

        assertEquals(1L, studentDTO.getStudentID());
        assertEquals("Meier", studentDTO.getLastName());
        assertEquals(LocalDate.of(2011, 1, 11), studentDTO.getBirthdate());
        assertEquals(userDTO.getUsername(), studentDTO.getUser().getUsername());
    }
}

