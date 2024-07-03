package IDTest;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsibilitiesIDTest {
    private ResponsibilitiesID responsibilitiesID1;
    private ResponsibilitiesID responsibilitiesID2;
    private ResponsibilitiesID responsibilitiesID3;
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

        responsibilitiesID1 = new ResponsibilitiesID();
        responsibilitiesID1.setVacancy(vacancy1);
        responsibilitiesID1.setSerialNumber(12345);

        responsibilitiesID2 = new ResponsibilitiesID();
        responsibilitiesID2.setVacancy(vacancy1);
        responsibilitiesID2.setSerialNumber(12345);

        responsibilitiesID3 = new ResponsibilitiesID();
        responsibilitiesID3.setVacancy(vacancy2);
        responsibilitiesID3.setSerialNumber(67890);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(responsibilitiesID1, responsibilitiesID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(responsibilitiesID1, responsibilitiesID2);
    }

    @Test
    void testEqualsDifferentValues() {
        assertNotEquals(responsibilitiesID1, responsibilitiesID3);
    }
    @Test
    void testEqualsDifferentSerialValues() {
        assertNotEquals(responsibilitiesID1.getSerialNumber(), responsibilitiesID3.getSerialNumber());
    }
    @Test
    void testEqualsDifferentStudentValues() {
        assertNotEquals(responsibilitiesID1.getVacancy(), responsibilitiesID3.getVacancy());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(responsibilitiesID1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(responsibilitiesID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(responsibilitiesID1.hashCode(), responsibilitiesID2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        assertNotEquals(responsibilitiesID1.hashCode(), responsibilitiesID3.hashCode());
    }
}

