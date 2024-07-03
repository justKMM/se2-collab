package IDTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.SkillID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillIDTest {
    private SkillID skillID1;
    private SkillID skillID2;
    private SkillID skillID3;
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
    void gettersAndSetters(){
        assertEquals("Tester", student1.getLastName());
        assertEquals(1L, student1.getStudentID());
        assertEquals("Test", student2.getLastName());
        assertEquals(2L, student2.getStudentID());
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(skillID1, skillID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(skillID1, skillID2);
    }

    @Test
    void testEqualsDifferentValues() {
        assertNotEquals(skillID1, skillID3);
    }
    @Test
    void testEqualsDifferentStudentValues() {
        assertNotEquals(skillID1.getStudent(), skillID3.getStudent());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(skillID1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(skillID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(skillID1.hashCode(), skillID2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        assertNotEquals(skillID1.hashCode(), skillID3.hashCode());
    }
}

