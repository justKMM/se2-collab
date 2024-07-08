package traits_test;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.RequirementsID;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequirementsTest {

    private Requirements requirements;
    private Vacancy vacancy;

    @BeforeEach
    void setUp() {
        vacancy = new Vacancy();
        vacancy.setVacancyID(1L);
        vacancy.setTitle("Software Tester");

        requirements = new Requirements();
        requirements.setVacancy(vacancy);
        requirements.setSerialNumber(123);
        requirements.setRequirementsName("Java");
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(123, requirements.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        requirements.setSerialNumber(456);
        assertEquals(456, requirements.getSerialNumber());
    }

    @Test
    void testGetRequirementsName() {
        assertEquals("Java", requirements.getRequirementsName());
    }

    @Test
    void testSetRequirementsName() {
        requirements.setRequirementsName("Python");
        assertEquals("Python", requirements.getRequirementsName());
    }

    @Test
    void testGetId() {
        RequirementsID id = requirements.getId();
        assertEquals(vacancy, id.getVacancy());
        assertEquals(123, id.getSerialNumber());
    }

    @Test
    void testRequirementsNotNull() {
        assertNotNull(requirements);
    }

    @Test
    void testGetVacancy() {
        assertEquals(vacancy, requirements.getVacancy());
    }

    @Test
    void testSetVacancy() {
        Vacancy newVacancy = new Vacancy();
        newVacancy.setVacancyID(2L);
        newVacancy.setTitle("Data Scientist");
        requirements.setVacancy(newVacancy);
        assertEquals(newVacancy, requirements.getVacancy());
    }
}
