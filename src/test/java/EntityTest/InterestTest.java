package EntityTest;

import hbrs.se2.collhbrs.model.entity.Interest;
import hbrs.se2.collhbrs.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class InterestTest {

    Interest interest;
    Interest interest1;

    @Mock
    Student student;

    @BeforeEach
    public void setUp() {
        interest = new Interest();
        interest.setStudent(student);
        interest.setSerialNumber(10);
        interest.setInterestName("Platzhalter");

        interest1 = new Interest();
        interest1.setStudent(student);
        interest1.setSerialNumber(12);
        interest1.setInterestName("Platzhalter1");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(student, interest.getStudent());
        assertEquals(10, interest.getSerialNumber());
        assertEquals("Platzhalter", interest.getInterestName());

        interest.setSerialNumber(11);
        assertNotEquals(10, interest.getSerialNumber());
        assertEquals(11, interest.getSerialNumber());
    }

    @Test
    public void testEquals() {
        assertEquals(interest, interest);
        assertEquals(interest1, interest1);
        assertNotEquals(interest, interest1);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(interest.hashCode(), interest1.hashCode());
        assertEquals(interest.hashCode(), interest.hashCode());
    }
}
