package IDTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.FirstNameID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstNameIDTest {
    private FirstNameID firstNameID1;
    private FirstNameID firstNameID2;
    private FirstNameID firstNameID3;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student();
        student1.setStudentID(1L);
        student1.setLastName("Tester");

        student2 = new Student();
        student2.setStudentID(2L);
        student2.setLastName("Test");

        firstNameID1 = new FirstNameID();
        firstNameID1.setStudent(student1);
        firstNameID1.setSerialNumber(12345);

        firstNameID2 = new FirstNameID();
        firstNameID2.setStudent(student1);
        firstNameID2.setSerialNumber(12345);

        firstNameID3 = new FirstNameID();
        firstNameID3.setStudent(student2);
        firstNameID3.setSerialNumber(67890);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(firstNameID1, firstNameID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(firstNameID1, firstNameID2);
    }

    @Test
    void testEqualsDifferentValues() {
        assertNotEquals(firstNameID1, firstNameID3);
    }
    @Test
    void testEqualsDifferentSerialValues() {
        assertNotEquals(firstNameID1.getSerialNumber(), firstNameID3.getSerialNumber());
    }
    @Test
    void testEqualsDifferentStudentValues() {
        assertNotEquals(firstNameID1.getStudent(), firstNameID3.getStudent());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(firstNameID1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(firstNameID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(firstNameID1.hashCode(), firstNameID2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        assertNotEquals(firstNameID1.hashCode(), firstNameID3.hashCode());
    }
}

