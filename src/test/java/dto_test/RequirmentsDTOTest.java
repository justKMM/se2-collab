package dto_test;

import hbrs.se2.collhbrs.model.dto.RequirmentsDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequirmentsDTOTest {

    private RequirmentsDTO requirementsDTO;

    @BeforeEach
    void setUp() {
        // Erstellen von Vacancy
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(1L);
        vacancy.setTitle("Software Tester");

        // Erstellen von Requirements
        Requirements requirements = new Requirements();
        requirements.setVacancy(vacancy);
        requirements.setSerialNumber(101);
        requirements.setRequirementsName("Java Tester");

        // Erstellen von RequirementsDTO
        this.requirementsDTO = new RequirmentsDTO(requirements);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, requirementsDTO.getVacancy().getVacancyID());
        assertEquals("Software Tester", requirementsDTO.getVacancy().getTitle());
        assertEquals(101, requirementsDTO.getSerialNumber());
        assertEquals("Java Tester", requirementsDTO.getRequirementsName());

        Vacancy newVacancy = new Vacancy();
        newVacancy.setVacancyID(2L);
        newVacancy.setTitle("Data Tester");

        requirementsDTO.setVacancy(newVacancy);
        requirementsDTO.setSerialNumber(202);
        requirementsDTO.setRequirementsName("Python Tester");

        assertEquals(2L, requirementsDTO.getVacancy().getVacancyID());
        assertEquals("Data Tester", requirementsDTO.getVacancy().getTitle());
        assertEquals(202, requirementsDTO.getSerialNumber());
        assertEquals("Python Tester", requirementsDTO.getRequirementsName());
    }
}

