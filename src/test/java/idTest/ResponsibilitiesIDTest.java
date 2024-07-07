package idTest;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResponsibilitiesIDTest extends GenericIDTest {
    private ResponsibilitiesID responsibilitiesID1;
    private ResponsibilitiesID responsibilitiesID2;
    private ResponsibilitiesID responsibilitiesID3;

    @BeforeEach
    void setUp() {
        Vacancy vacancy1 = createVacancy(1L, "Job Title 1");
        Vacancy vacancy2 = createVacancy(2L, "Job Title 2");

        responsibilitiesID1 = createResponsibilitiesID(vacancy1, 12345);
        responsibilitiesID2 = createResponsibilitiesID(vacancy1, 12345);
        responsibilitiesID3 = createResponsibilitiesID(vacancy2, 67890);
    }

    private Vacancy createVacancy(Long id, String title) {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(id);
        vacancy.setTitle(title);
        return vacancy;
    }

    private ResponsibilitiesID createResponsibilitiesID(Vacancy vacancy, int serialNumber) {
        ResponsibilitiesID responsibilitiesID = new ResponsibilitiesID();
        responsibilitiesID.setSerialNumber(serialNumber);
        responsibilitiesID.setVacancy(vacancy); // Ensure this method exists
        return responsibilitiesID;
    }

    @Test
    void testEqualsSameObject() {
        testEqualsSameObject(responsibilitiesID1);
    }

    @Test
    void testEqualsSameValues() {
        testEqualsSameValues(responsibilitiesID1, responsibilitiesID2);
    }

    @Test
    void testEqualsDifferentValues() {
        testEqualsDifferentValues(responsibilitiesID1, responsibilitiesID3);
    }

    @Test
    void testEqualsNull() {
        testEqualsNull(responsibilitiesID1);
    }

    @Test
    void testEqualsDifferentClass() {
        testEqualsDifferentClass(responsibilitiesID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        testHashCodeSameValues(responsibilitiesID1, responsibilitiesID2);
    }

    @Test
    void testHashCodeDifferentValues() {
        testHashCodeDifferentValues(responsibilitiesID1, responsibilitiesID3);
    }
}