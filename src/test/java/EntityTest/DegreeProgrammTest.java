package EntityTest;


import hbrs.se2.collhbrs.model.entity.DegreeProgramm;
import hbrs.se2.collhbrs.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DegreeProgrammTest {

    DegreeProgramm degreeProgramm;
    DegreeProgramm degreeProgramm1;

    @Mock
    Student student;

    @BeforeEach
    public void setUp() {
        degreeProgramm = new DegreeProgramm();
        degreeProgramm.setStudent(student);
        degreeProgramm.setSerialNumber(100);
        degreeProgramm.setDegreeProgrammName("BWI");

        degreeProgramm1 = new DegreeProgramm();
        degreeProgramm1.setStudent(student);
        degreeProgramm1.setSerialNumber(101);
        degreeProgramm1.setDegreeProgrammName("BI");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(student, degreeProgramm.getStudent());
        assertEquals(100, degreeProgramm.getSerialNumber());
        assertEquals("BWI", degreeProgramm.getDegreeProgrammName());

        assertEquals(student, degreeProgramm1.getStudent());
        assertEquals(101, degreeProgramm1.getSerialNumber());
        assertEquals("BI", degreeProgramm1.getDegreeProgrammName());
    }

    @Test
    void testEquals() {
        assertEquals(degreeProgramm, degreeProgramm);
        assertEquals(degreeProgramm1, degreeProgramm1);
        assertNotEquals(degreeProgramm, degreeProgramm1);
    }

    @Test
    void testHashCode() {
        assertNotEquals(degreeProgramm.hashCode(), degreeProgramm1.hashCode());
        assertEquals(degreeProgramm.hashCode(), degreeProgramm.hashCode());
    }

}
