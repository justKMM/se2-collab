package entityTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StudentTest {

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
    void testSettersandGetters() {
        assertEquals(1L, student.getStudentID());
        assertEquals(user, student.getUser());
        assertEquals("Bill", student.getLastName());
        assertEquals(LocalDate.of(2011, 5, 12), student.getBirthdate());

        student.setLastName("Bil");
        assertEquals("Bil", student.getLastName());
    }

    @Test
    void testEquals() {
        assertEquals(student, student);
        assertEquals(student1, student1);
        assertNotEquals(student, student1);
    }

    @Test
    void testHashCode() {
        assertEquals(student.hashCode(), student.hashCode());
        assertNotEquals(student.hashCode(), student1.hashCode());
    }

}
