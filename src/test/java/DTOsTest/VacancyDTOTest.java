package DTOsTest;

import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VacancyDTOTest {

    @Mock
    BusinessDTO businessDTO;
    Business business;
    private VacancyDTO vacancyDTO;

    @BeforeEach
    void setUp() {
        businessDTO = new BusinessDTO(new Business());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, vacancyDTO.getVacancyID());
        assertNull(vacancyDTO.getBusiness());
        assertNull(vacancyDTO.getTitle());
        assertNull(vacancyDTO.getDescription());

        vacancyDTO.setVacancyID(1L);
        vacancyDTO.setBusiness(business);
        vacancyDTO.setTitle("Software Tester");
        vacancyDTO.setDescription("Test software.");

        assertEquals(1L, vacancyDTO.getVacancyID());
        assertEquals(business, vacancyDTO.getBusiness());
        assertEquals("Software Tester", vacancyDTO.getTitle());
        assertEquals("Test software.", vacancyDTO.getDescription());
    }
}

