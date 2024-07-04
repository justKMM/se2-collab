package IDTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.SkillID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkillIDTest extends GenericIDTest {
    private SkillID skillID1;
    private SkillID skillID2;
    private SkillID skillID3;

    @BeforeEach
    void setUp() {
        Student student1 = new Student();
        student1.setStudentID(1L);
        student1.setLastName("Tester");

        Student student2 = new Student();
        student2.setStudentID(2L);
        student2.setLastName("Test");

        skillID1 = new SkillID();
        skillID1.setStudent(student1);
        skillID1.setSerialNumber(12345);

        skillID2 = new SkillID();
        skillID2.setStudent(student1);
        skillID2.setSerialNumber(12345);

        skillID3 = new SkillID();
        skillID3.setStudent(student2);
        skillID3.setSerialNumber(67890);
    }

    @Test
    void testEqualsSameObject() {
        testEqualsSameObject(skillID1);
    }

    @Test
    void testEqualsSameValues() {
        testEqualsSameValues(skillID1, skillID2);
    }

    @Test
    void testEqualsDifferentValues() {
        testEqualsDifferentValues(skillID1, skillID3);
    }

    @Test
    void testEqualsDifferentStudentValues() {
        Assertions.assertNotEquals(skillID1.getStudent(), skillID3.getStudent());
    }

    @Test
    void testEqualsNull() {
        testEqualsNull(skillID1);
    }

    @Test
    void testEqualsDifferentClass() {
        testEqualsDifferentClass(skillID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        testHashCodeSameValues(skillID1, skillID2);
    }

    @Test
    void testHashCodeDifferentValues() {
        testHashCodeDifferentValues(skillID1, skillID3);
    }
}