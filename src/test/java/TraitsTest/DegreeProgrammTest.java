package TraitsTest;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.DegreeProgrammID;
import hbrs.se2.collhbrs.model.entity.traits.DegreeProgramm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DegreeProgrammTest {

    private DegreeProgramm degreeProgramm;
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentID(1L);
        student.setLastName("Tester");

        degreeProgramm = new DegreeProgramm();
        degreeProgramm.setStudent(student);
        degreeProgramm.setSerialNumber(123);
        degreeProgramm.setDegreeProgramName("Computer Tester");
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(123, degreeProgramm.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        degreeProgramm.setSerialNumber(456);
        assertEquals(456, degreeProgramm.getSerialNumber());
    }

    @Test
    void testGetDegreeProgramName() {
        assertEquals("Computer Tester", degreeProgramm.getDegreeProgramName());
    }

    @Test
    void testSetDegreeProgramName() {
        degreeProgramm.setDegreeProgramName("Data Tester");
        assertEquals("Data Tester", degreeProgramm.getDegreeProgramName());
    }

    @Test
    void testGetId() {
        DegreeProgrammID id = degreeProgramm.getId();
        assertEquals(student, id.getStudent());
        assertEquals(123, id.getSerialNumber());
    }

    @Test
    void testDegreeProgrammNotNull() {
        assertNotNull(degreeProgramm);
    }

    @Test
    void testGetStudent() {
        assertEquals(student, degreeProgramm.getStudent());
    }

    @Test
    void testSetStudent() {
        Student newStudent = new Student();
        newStudent.setStudentID(2L);
        newStudent.setLastName("Tester");
        degreeProgramm.setStudent(newStudent);
        assertEquals(newStudent, degreeProgramm.getStudent());
    }
}

