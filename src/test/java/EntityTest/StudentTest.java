package EntityTest;

import java.time.LocalDate;
import hbrs.se2.collhbrs.entity.Student;
import hbrs.se2.collhbrs.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    Student student;
    Student student1;

    @Mock
    User user;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setStudentID(1L);
        student.setUser(user);
        student.setLastName("Bill");
        student.setBirthdate(LocalDate.of(2011, 5, 12));

        student1 = new Student();
        student1.setStudentID(2L);
        student1.setUser(user);
        student1.setLastName("Billy");
        student1.setBirthdate(LocalDate.of(2011, 1, 11));
    }
    @Test
    public void testSettersandGetters() {
        assertEquals(1L,student.getStudentID());
        assertEquals(user,student.getUser());
        assertEquals("Bill",student.getLastName());
        assertEquals(LocalDate.of(2011, 5, 12),student.getBirthdate());

        student.setLastName("Bil");
        assertEquals("Bil", student.getLastName());
    }
    @Test
    public void testEquals() {
        assertTrue(student.equals(student));
        assertTrue(student1.equals(student1));
        assertFalse(student.equals(student1));
    }
    @Test
    public void testHashCode() {
        assertEquals(student.hashCode(),student.hashCode());
        assertNotEquals(student.hashCode(),student1.hashCode());
    }

}
