package DTOsTest;

import hbrs.se2.collhbrs.model.dto.FirstNameDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FirstNameDTOTest {

    @Mock
    StudentDTO studentDTO;
    private FirstNameDTO firstNameDTO;

    @BeforeEach
    void setUp() {
        firstNameDTO = new FirstNameDTO(new FirstName());
        studentDTO = new StudentDTO(new Student());
    }

    @Test
    void testGettersAndSetters() {
        assertNull(firstNameDTO.getStudent());
        assertEquals(0, firstNameDTO.getSerialNumber());
        assertNull(firstNameDTO.getFirstNameName());

        firstNameDTO.setStudent(studentDTO.getStudent());
        firstNameDTO.setSerialNumber(123);
        firstNameDTO.setFirstNameName("John");

        assertEquals(studentDTO.getStudent(), firstNameDTO.getStudent());
        assertEquals(123, firstNameDTO.getSerialNumber());
        assertEquals("John", firstNameDTO.getFirstNameName());
    }
}

