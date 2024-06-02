package DTOsTest;

import hbrs.se2.collhbrs.model.dto.imp.StudentDTOImpl;
import hbrs.se2.collhbrs.model.dto.imp.UserDTOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentDTOImplTest {

    private StudentDTOImpl studentDTO;

    @Mock
    UserDTOImpl userDTO;

    @BeforeEach
    void setUp() {
        studentDTO = new StudentDTOImpl();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, studentDTO.getStudentID());
        assertNull( studentDTO.getUser());
        assertNull(studentDTO.getLastName());
        assertNull(studentDTO.getBirthdate());


        studentDTO.setStudentID(1L);
        studentDTO.setLastName("Meier");
        studentDTO.setBirthdate(LocalDate.of(2011, 1, 11));
        studentDTO.setUser(userDTO);

        assertEquals(1L, studentDTO.getStudentID());
        assertEquals("Meier", studentDTO.getLastName());
        assertEquals(LocalDate.of(2011, 1, 11), studentDTO.getBirthdate());
        assertEquals(userDTO, studentDTO.getUser());


    }
}

