package id_test;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.FirstNameID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FirstNameIDTest extends GenericIDTest {
    private FirstNameID firstNameID1;
    private FirstNameID firstNameID2;
    private FirstNameID firstNameID3;

    @BeforeEach
    void setUp() {
        Student student1 = new Student();
        student1.setStudentID(1L);
        student1.setLastName("Tester");

        Student student2 = new Student();
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
        testEqualsSameObject(firstNameID1);
    }

    @Test
    void testEqualsSameValues() {
        testEqualsSameValues(firstNameID1, firstNameID2);
    }

    @Test
    void testEqualsDifferentValues() {
        testEqualsDifferentValues(firstNameID1, firstNameID3);
    }

    @Test
    void testEqualsNull() {
        testEqualsNull(firstNameID1);
    }

    @Test
    void testEqualsDifferentClass() {
        testEqualsDifferentClass(firstNameID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        testHashCodeSameValues(firstNameID1, firstNameID2);
    }

    @Test
    void testHashCodeDifferentValues() {
        testHashCodeDifferentValues(firstNameID1, firstNameID3);
    }
}