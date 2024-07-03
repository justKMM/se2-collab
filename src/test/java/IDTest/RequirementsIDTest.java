package IDTest;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementsIDTest {
    private RequirementsID requirementsID1;
    private RequirementsID requirementsID2;
    private RequirementsID requirementsID3;
    private Vacancy vacancy1;
    private Vacancy vacancy2;


    @BeforeEach
    void setUp() {
        vacancy1 = new Vacancy();
        vacancy1.setVacancyID(1L);
        vacancy1.setTitle("Software Tester");

        vacancy2 = new Vacancy();
        vacancy2.setVacancyID(2L);
        vacancy2.setTitle("Tester");

        requirementsID1 = new RequirementsID();
        requirementsID1.setVacancy(vacancy1);
        requirementsID1.setSerialNumber(12345);

        requirementsID2 = new RequirementsID();
        requirementsID2.setVacancy(vacancy1);
        requirementsID2.setSerialNumber(12345);

        requirementsID3 = new RequirementsID();
        requirementsID3.setVacancy(vacancy2);
        requirementsID3.setSerialNumber(67890);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(requirementsID1, requirementsID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(requirementsID1, requirementsID2);
    }

    @Test
    void testEqualsDifferentValues() {
        assertNotEquals(requirementsID1, requirementsID3);
    }
    @Test
    void testEqualsDifferentSerialValues() {
        assertNotEquals(requirementsID1.getSerialNumber(), requirementsID3.getSerialNumber());
    }
    @Test
    void testEqualsDifferentStudentValues() {
        assertNotEquals(requirementsID1.getVacancy(), requirementsID3.getVacancy());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(requirementsID1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(requirementsID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(requirementsID1.hashCode(), requirementsID2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        assertNotEquals(requirementsID1.hashCode(), requirementsID3.hashCode());
    }
}
