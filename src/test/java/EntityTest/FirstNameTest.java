package EntityTest;

import hbrs.se2.collhbrs.model.entity.FirstName;
import hbrs.se2.collhbrs.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FirstNameTest {

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
    void testSettersandGetters() {
        assertEquals(student, firstName.getStudent());
        assertEquals(10, firstName.getSerialNumber());
        assertEquals("Hans Dieter", firstName.getFirstNameName());

        firstName.setStudent(student1);
        assertEquals(student1, firstName.getStudent());
    }

    @Test
    void testEquals() {
        assertEquals(firstName, firstName);
        assertEquals(firstName1, firstName1);
        assertEquals(firstName2, firstName2);
        assertEquals(firstName1, firstName2);
        assertNotEquals(firstName, firstName1);
        assertNotEquals(firstName, firstName2);
        assertNotEquals(firstName, null);
        assertNotEquals(firstName, "lol");
    }

    @Test
    void testHashCode() {
        assertNotEquals(firstName.hashCode(), firstName2.hashCode());
        assertEquals(firstName1.hashCode(), firstName2.hashCode());
    }
}
