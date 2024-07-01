package DTOsTest;

import hbrs.se2.collhbrs.model.dto.DegreeProgrammDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.DegreeProgramm;
import hbrs.se2.collhbrs.model.entity.Student;
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
        studentDTO = new StudentDTO(new Student());
    }

    @Test
    void testGettersAndSetters() {
        assertNull(degreeProgrammDTO.getStudent());
        assertEquals(0, degreeProgrammDTO.getSerialNumber());
        assertNull(degreeProgrammDTO.getDegreeProgrammName());

        degreeProgrammDTO.setStudent(new Student());
        degreeProgrammDTO.setSerialNumber(123);
        degreeProgrammDTO.setDegreeProgrammName("Se2");

        assertEquals(studentDTO.getStudent(), degreeProgrammDTO.getStudent());
        assertEquals(123, degreeProgrammDTO.getSerialNumber());
        assertEquals("Se2", degreeProgrammDTO.getDegreeProgrammName());
    }
}

