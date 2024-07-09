package hbrs.se2.collhbrs.model.dto;

import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponsibilitiesDTOTest {

    @Test
    public void testResponsibilitiesDTOConstructorAndGetters() {
        Vacancy vacancy = new Vacancy();
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(1);
        responsibilities.setResponsibilitiesName("Manage team");

        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(responsibilities);

        assertEquals(vacancy, dto.getVacancy());
        assertEquals(1, dto.getSerialNumber());
        assertEquals("Manage team", dto.getResponsibilitiesName());
    }

    @Test
    public void testGetResponsibilities() {
        Vacancy vacancy = new Vacancy();
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(1);
        responsibilities.setResponsibilitiesName("Manage team");

        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(responsibilities);
        Responsibilities newResponsibilities = dto.getResponsibilities();

        assertEquals(vacancy, newResponsibilities.getVacancy());
        assertEquals(1, newResponsibilities.getSerialNumber());
        assertEquals("Manage team", newResponsibilities.getResponsibilitiesName());
    }

    @Test
    public void testToString() {
        Vacancy vacancy = new Vacancy();
        Responsibilities responsibilities = new Responsibilities();
        responsibilities.setVacancy(vacancy);
        responsibilities.setSerialNumber(1);
        responsibilities.setResponsibilitiesName("Manage team");

        ResponsibilitiesDTO dto = new ResponsibilitiesDTO(responsibilities);
        String expectedString = "ResponsibilitiesDTO{" +
                "vacancy=" + vacancy +
                ", serialNumber=" + 1 +
                ", responsibilitiesName='Manage team'}";

        assertEquals(expectedString, dto.toString());
    }

    @Test
    public void testSetters() {
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