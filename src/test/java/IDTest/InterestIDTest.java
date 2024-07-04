package IDTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.InterestID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InterestIDTest {
    private InterestID interestID1;
    private InterestID interestID2;
    private InterestID interestID3;

    @BeforeEach
    void setUp() {
        Student student1 = new Student();
        student1.setStudentID(1L);
        student1.setLastName("Tester");

        Student student2 = new Student();
        student2.setStudentID(2L);
        student2.setLastName("Test");

        interestID1 = new InterestID();
        interestID1.setStudent(student1);
        interestID1.setSerialNumber(12345);

        interestID2 = new InterestID();
        interestID2.setStudent(student1);
        interestID2.setSerialNumber(12345);

        interestID3 = new InterestID();
        interestID3.setStudent(student2);
        interestID3.setSerialNumber(67890);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(interestID1, interestID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(interestID1, interestID2);
    }

    @Test
    void testEqualsDifferentValues() {
        assertNotEquals(interestID1, interestID3);
    }

    @Test
    void testEqualsDifferentSerialValues() {
        assertNotEquals(interestID1.getSerialNumber(), interestID3.getSerialNumber());
    }

    @Test
    void testEqualsDifferentStudentValues() {
        assertNotEquals(interestID1.getStudent(), interestID3.getStudent());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, interestID1);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(interestID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(interestID1.hashCode(), interestID2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        assertNotEquals(interestID1.hashCode(), interestID3.hashCode());
    }
}

