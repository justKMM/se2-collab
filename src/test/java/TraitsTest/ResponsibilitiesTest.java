package TraitsTest;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.ids.ResponsibilitiesID;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResponsibilitiesTest {

    private Responsibilities responsibilities;
    private Vacancy vacancy;

    @BeforeEach
    void setUp() {
        vacancy = new Vacancy();
        vacancy.setVacancyID(1L);
        vacancy.setTitle("Software Tester");

        responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(123);
        responsibilities.setResponsibilitiesName("Test software systems");
    }

    @Test
    void testGetSerialNumber() {
        assertEquals(123, responsibilities.getSerialNumber());
    }

    @Test
    void testSetSerialNumber() {
        responsibilities.setSerialNumber(456);
        assertEquals(456, responsibilities.getSerialNumber());
    }

    @Test
    void testGetResponsibilitiesName() {
        assertEquals("Test software systems", responsibilities.getResponsibilitiesName());
    }

    @Test
    void testSetResponsibilitiesName() {
        responsibilities.setResponsibilitiesName("Test software");
        assertEquals("Test software", responsibilities.getResponsibilitiesName());
    }

    @Test
    void testGetId() {
        ResponsibilitiesID id = responsibilities.getId();
        assertEquals(vacancy, id.getVacancy());
        assertEquals(123, id.getSerialNumber());
    }

    @Test
    void testResponsibilitiesNotNull() {
        assertNotNull(responsibilities);
    }

    @Test
    void testGetVacancy() {
        assertEquals(vacancy, responsibilities.getVacancy());
    }

    @Test
    void testSetVacancy() {
        Vacancy newVacancy = new Vacancy();
        newVacancy.setVacancyID(2L);
        newVacancy.setTitle("Data Tester");
        responsibilities.setVacancy(newVacancy);
        assertEquals(newVacancy, responsibilities.getVacancy());
    }
}

