package dto_test;

import hbrs.se2.collhbrs.model.dto.DegreeProgrammDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.model.entity.traits.DegreeProgramm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DegreeProgrammDTOTest {

    @Mock
    StudentDTO studentDTO;
    private DegreeProgrammDTO degreeProgrammDTO;

    @BeforeEach
    void setUp() {
        degreeProgrammDTO = new DegreeProgrammDTO(new DegreeProgramm());
        User user = new User();
        user.setUserID(1L);
        user.setUsername("testuser");
        Student student = new Student();
        student.setUser(user);
        studentDTO = new StudentDTO(student);
        degreeProgrammDTO.setStudent(student);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, degreeProgrammDTO.getSerialNumber());
        assertNull(degreeProgrammDTO.getDegreeProgrammName());

        degreeProgrammDTO.setSerialNumber(123);
        degreeProgrammDTO.setDegreeProgrammName("Se2");

        assertEquals(studentDTO.getStudent(), degreeProgrammDTO.getStudent());
        assertEquals(123, degreeProgrammDTO.getSerialNumber());
        assertEquals("Se2", degreeProgrammDTO.getDegreeProgrammName());
    }
}
