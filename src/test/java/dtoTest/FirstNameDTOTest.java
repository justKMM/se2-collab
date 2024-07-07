package dtoTest;

import hbrs.se2.collhbrs.model.dto.FirstNameDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
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
        User user = new User();
        user.setUserID(1L);
        user.setUsername("testuser");
        Student student = new Student();
        student.setUser(user);
        studentDTO = new StudentDTO(student);
        FirstName firstName = new FirstName();
        firstName.setStudent(student);
        firstNameDTO = new FirstNameDTO(firstName);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, firstNameDTO.getSerialNumber());
        assertNull(firstNameDTO.getFirstNameName());

        firstNameDTO.setSerialNumber(123);
        firstNameDTO.setFirstNameName("John");

        assertEquals(studentDTO.getStudent(), firstNameDTO.getStudent());
        assertEquals(123, firstNameDTO.getSerialNumber());
        assertEquals("John", firstNameDTO.getFirstNameName());
    }
}
