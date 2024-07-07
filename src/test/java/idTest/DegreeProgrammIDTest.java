package idTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.DegreeProgrammID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DegreeProgrammIDTest extends GenericIDTest {
    private DegreeProgrammID degreeProgrammID1;
    private DegreeProgrammID degreeProgrammID2;
    private DegreeProgrammID degreeProgrammID3;

    @BeforeEach
    void setUp() {
        Student student1 = new Student();
        student1.setStudentID(1L);
        student1.setLastName("Tester");

        Student student2 = new Student();
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
    void testEqualsSameObject() {
        testEqualsSameObject(degreeProgrammID1);
    }

    @Test
    void testEqualsSameValues() {
        testEqualsSameValues(degreeProgrammID1, degreeProgrammID2);
    }

    @Test
    void testEqualsDifferentValues() {
        testEqualsDifferentValues(degreeProgrammID1, degreeProgrammID3);
    }

    @Test
    void testEqualsNull() {
        testEqualsNull(degreeProgrammID1);
    }

    @Test
    void testEqualsDifferentClass() {
        testEqualsDifferentClass(degreeProgrammID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        testHashCodeSameValues(degreeProgrammID1, degreeProgrammID2);
    }

    @Test
    void testHashCodeDifferentValues() {
        testHashCodeDifferentValues(degreeProgrammID1, degreeProgrammID3);
    }
}