package dto_test;

import hbrs.se2.collhbrs.model.dto.ResponsibilitiesDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsibilitiesDTOTest {

    private static final String RESPONSIBILITIES_NAME = "Manage team";

    @Test
    void testResponsibilitiesDTOConstructorAndGetters() {
        Vacancy vacancy = new Vacancy();
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(1);
        responsibilities.setResponsibilitiesName(RESPONSIBILITIES_NAME);

        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(responsibilities);

        assertEquals(vacancy, dto.getVacancy());
        assertEquals(1, dto.getSerialNumber());
        assertEquals(RESPONSIBILITIES_NAME, dto.getResponsibilitiesName());
    }

    @Test
    void testGetResponsibilities() {
        Vacancy vacancy = new Vacancy();
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(1);
        responsibilities.setResponsibilitiesName(RESPONSIBILITIES_NAME);

        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(responsibilities);
        Responsibilities newResponsibilities = dto.getResponsibilities();

        assertEquals(vacancy, newResponsibilities.getVacancy());
        assertEquals(1, newResponsibilities.getSerialNumber());
        assertEquals(RESPONSIBILITIES_NAME, newResponsibilities.getResponsibilitiesName());
    }

    @Test
    void testToString() {
        Vacancy vacancy = new Vacancy();
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(1);
        responsibilities.setResponsibilitiesName(RESPONSIBILITIES_NAME);

        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(responsibilities);
        String expectedString = "ResponsibilitiesDTO{" +
                "vacancy=" + vacancy +
                ", serialNumber=" + 1 +
                ", responsibilitiesName='" + RESPONSIBILITIES_NAME + "'}";

        assertEquals(expectedString, dto.toString());
    }

    @Test
    void testSetters() {
        Vacancy vacancy = new Vacancy();
        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(new Responsibilities());

        dto.setVacancy(vacancy);
        dto.setSerialNumber(2);
        dto.setResponsibilitiesName("Develop software");

        assertEquals(vacancy, dto.getVacancy());
        assertEquals(2, dto.getSerialNumber());
        assertEquals("Develop software", dto.getResponsibilitiesName());
    }
}