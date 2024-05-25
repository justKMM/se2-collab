package EntityTest;

import hbrs.se2.collhbrs.entity.Business;
import hbrs.se2.collhbrs.entity.Vacancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class VacancyTest {

    Vacancy vacancy;Vacancy vacancy1;


    @Mock
    Business business;

    @BeforeEach
    public void setUp() {
        vacancy = new Vacancy();
        vacancy.setVacancyID(1L);
        vacancy.setBusiness(business);
        vacancy.setTitel("Shk");
        vacancy.setDescription("Unterstützung bei der DB");

        vacancy1 = new Vacancy();
        vacancy1.setVacancyID(2L);
        vacancy1.setBusiness(business);
        vacancy1.setTitel("Shk1");
        vacancy1.setDescription("Unterstützung bei der DB1");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, vacancy.getVacancyID());
        assertEquals(business, vacancy.getBusiness());
        assertEquals("Shk", vacancy.getTitel());
        assertEquals("Unterstützung bei der DB", vacancy.getDescription());


    }
    @Test
    public void testEquals() {
        assertTrue(vacancy.equals(vacancy));
        assertTrue(vacancy.equals(vacancy));
        assertFalse(vacancy.equals(vacancy1));

    }
}
