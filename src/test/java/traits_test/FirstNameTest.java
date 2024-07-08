package traits_test;

import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.ids.FirstNameID;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FirstNameTest {

    private FirstName firstName;
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentID(1L);
        student.setLastName("Doe");

        firstName = new FirstName();
        firstName.setStudent(student);
        firstName.setSerialNumber(123);
        firstName.setFirstNameName("John");
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(123, firstName.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        firstName.setSerialNumber(456);
        assertEquals(456, firstName.getSerialNumber());
    }

    @Test
    void testGetFirstNameName() {
        assertEquals("John", firstName.getFirstNameName());
    }

    @Test
    void testSetFirstNameName() {
        firstName.setFirstNameName("Jane");
        assertEquals("Jane", firstName.getFirstNameName());
    }

    @Test
    void testGetId() {
        FirstNameID id = firstName.getId();
        assertEquals(student, id.getStudent());
        assertEquals(123, id.getSerialNumber());
    }

    @Test
    void testFirstNameNotNull() {
        assertNotNull(firstName);
    }

    @Test
    void testGetStudent() {
        assertEquals(student, firstName.getStudent());
    }

    @Test
    void testSetStudent() {
        Student newStudent = new Student();
        newStudent.setStudentID(2L);
        newStudent.setLastName("Smith");
        firstName.setStudent(newStudent);
        assertEquals(newStudent, firstName.getStudent());
    }
}

