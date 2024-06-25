package DTOsTest;

import hbrs.se2.collhbrs.model.dto.InterestDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Interest;
import hbrs.se2.collhbrs.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InterestDTOTest {

    private InterestDTO interestDTO;

    @Mock
    StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        interestDTO = new InterestDTO(new Interest());
        studentDTO = new StudentDTO(new Student());
    }

    @Test
    void testGettersAndSetters() {
        assertNull(interestDTO.getStudent());
        assertEquals(0, interestDTO.getSerialNumber());
        assertNull(interestDTO.getInterestName());

        interestDTO.setStudent(studentDTO.getStudent());
        interestDTO.setSerialNumber(123);
        interestDTO.setInterestName("Testing");

        assertEquals(studentDTO.getStudent(), interestDTO.getStudent());
        assertEquals(123, interestDTO.getSerialNumber());
        assertEquals("Testing", interestDTO.getInterestName());
    }
}

