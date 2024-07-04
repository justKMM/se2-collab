package IDTest;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequirementsIDTest extends GenericIDTest {
    private RequirementsID requirementsID1;
    private RequirementsID requirementsID2;
    private RequirementsID requirementsID3;

    @BeforeEach
    void setUp() {
        Vacancy vacancy1 = createVacancy(1L, "Job Title 1");
        Vacancy vacancy2 = createVacancy(2L, "Job Title 2");

        requirementsID1 = createRequirementsID(vacancy1, 12345);
        requirementsID2 = createRequirementsID(vacancy1, 12345);
        requirementsID3 = createRequirementsID(vacancy2, 67890);
    }

    private Vacancy createVacancy(Long id, String title) {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(id);
        vacancy.setTitle(title);
        return vacancy;
    }

    private RequirementsID createRequirementsID(Vacancy vacancy, int serialNumber) {
        RequirementsID requirementsID = new RequirementsID();
        requirementsID.setSerialNumber(serialNumber);
        requirementsID.setVacancy(vacancy);
        return requirementsID;
    }

    @Test
    void testEqualsSameObject() {
        testEqualsSameObject(requirementsID1);
    }

    @Test
    void testEqualsSameValues() {
        testEqualsSameValues(requirementsID1, requirementsID2);
    }

    @Test
    void testEqualsDifferentValues() {
        testEqualsDifferentValues(requirementsID1, requirementsID3);
    }

    @Test
    void testEqualsNull() {
        testEqualsNull(requirementsID1);
    }

    @Test
    void testEqualsDifferentClass() {
        testEqualsDifferentClass(requirementsID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        testHashCodeSameValues(requirementsID1, requirementsID2);
    }

    @Test
    void testHashCodeDifferentValues() {
        testHashCodeDifferentValues(requirementsID1, requirementsID3);
    }
}