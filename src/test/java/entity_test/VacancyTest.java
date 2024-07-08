package entity_test;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VacancyTest {

    Vacancy vacancy;
    Vacancy vacancy1;


    @Mock
    Business business;

    @BeforeEach
    public void setUp() {
        vacancy = new Vacancy();
        vacancy.setVacancyID(1L);
        vacancy.setBusiness(business);
        vacancy.setTitle("Shk");
        vacancy.setDescription("Unterstützung bei der DB");

        vacancy1 = new Vacancy();
        vacancy1.setVacancyID(2L);
        vacancy1.setBusiness(business);
        vacancy1.setTitle("Shk1");
        vacancy1.setDescription("Unterstützung bei der DB1");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, vacancy.getVacancyID());
        assertEquals(business, vacancy.getBusiness());
        assertEquals("Shk", vacancy.getTitle());
        assertEquals("Unterstützung bei der DB", vacancy.getDescription());


    }

    @Test
    void testEquals() {
        assertEquals(vacancy, vacancy);
        assertEquals(vacancy, vacancy);
        assertNotEquals(vacancy, vacancy1);
    }
}
