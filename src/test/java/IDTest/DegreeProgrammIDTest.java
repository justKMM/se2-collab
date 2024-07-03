package IDTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.DegreeProgrammID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeProgrammIDTest {
    private DegreeProgrammID degreeProgrammID1;
    private DegreeProgrammID degreeProgrammID2;
    private DegreeProgrammID degreeProgrammID3;
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

        degreeProgrammID1 = new DegreeProgrammID();
        degreeProgrammID1.setStudent(student1);
        degreeProgrammID1.setSerialNumber(12345);

        degreeProgrammID2 = new DegreeProgrammID();
        degreeProgrammID2.setStudent(student1);
        degreeProgrammID2.setSerialNumber(12345);

        degreeProgrammID3 = new DegreeProgrammID();
        degreeProgrammID3.setStudent(student2);
        degreeProgrammID3.setSerialNumber(67890);
    }
    @Test
    void gettersAndSetters(){
        assertEquals("Tester", student1.getLastName());
        assertEquals(1L, student1.getStudentID());
        assertEquals("Test", student2.getLastName());
        assertEquals(2L, student2.getStudentID());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(degreeProgrammID1, degreeProgrammID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(degreeProgrammID1, degreeProgrammID2);
    }

    @Test
    void testEqualsDifferentValues() {
        assertNotEquals(degreeProgrammID1, degreeProgrammID3);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(degreeProgrammID1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(degreeProgrammID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(degreeProgrammID1.hashCode(), degreeProgrammID2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        assertNotEquals(degreeProgrammID1.hashCode(), degreeProgrammID3.hashCode());
    }
}

