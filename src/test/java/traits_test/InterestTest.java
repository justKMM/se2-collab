package traits_test;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.InterestID;
import hbrs.se2.collhbrs.model.entity.traits.Interest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InterestTest {

    private Interest interest;
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentID(1L);
        student.setLastName("Test");

        interest = new Interest();
        interest.setStudent(student);
        interest.setSerialNumber(123);
        interest.setInterestName("Programming");
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(123, interest.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        interest.setSerialNumber(456);
        assertEquals(456, interest.getSerialNumber());
    }

    @Test
    void testGetInterestName() {
        assertEquals("Programming", interest.getInterestName());
    }

    @Test
    void testSetInterestName() {
        interest.setInterestName("Testing");
        assertEquals("Testing", interest.getInterestName());
    }

    @Test
    void testGetId() {
        InterestID id = interest.getId();
        assertEquals(student, id.getStudent());
        assertEquals(123, id.getSerialNumber());
    }

    @Test
    void testInterestNotNull() {
        assertNotNull(interest);
    }

    @Test
    void testGetStudent() {
        assertEquals(student, interest.getStudent());
    }

    @Test
    void testSetStudent() {
        Student newStudent = new Student();
        newStudent.setStudentID(2L);
        newStudent.setLastName("Tester");
        interest.setStudent(newStudent);
        assertEquals(newStudent, interest.getStudent());
    }
}

