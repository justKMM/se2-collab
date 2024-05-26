package EntityTest;

import hbrs.se2.collhbrs.entity.FirstName;
import hbrs.se2.collhbrs.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class FirstNameTest {

    FirstName firstName;
    FirstName firstName1;
    FirstName firstName2;

    @Mock
    Student student;

    @Mock
    Student student1;


    @BeforeEach
    public void setUp() {
        firstName = new FirstName();
        firstName.setStudent(student);
        firstName.setSerialNumber(10);
        firstName.setFirstNameName("Hans Dieter");

        firstName1 = new FirstName();
        firstName1.setStudent(student1);
        firstName1.setSerialNumber(11);
        firstName1.setFirstNameName("Peter Hans Dieter");

        firstName2 = new FirstName();
        firstName2.setStudent(student1);
        firstName2.setSerialNumber(11);
        firstName2.setFirstNameName("Peter Hans Dieter");
    }
    @Test
    public void testSettersandGetters() {
        assertEquals(student, firstName.getStudent());
        assertEquals(10, firstName.getSerialNumber());
        assertEquals("Hans Dieter", firstName.getFirstNameName());

        firstName.setStudent(student1);
        assertEquals(student1,firstName.getStudent());
    }
    @Test
    public void testEquals() {
        assertTrue(firstName.equals(firstName));
        assertTrue(firstName1.equals(firstName1));
        assertTrue(firstName1.equals(firstName2));
        assertFalse(firstName.equals(firstName2));
    }
    @Test
    public void testHashCode() {
        assertNotEquals(firstName.hashCode(),firstName2.hashCode());
        assertEquals(firstName1.hashCode(),firstName2.hashCode());
    }
}
