package EntityTest;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    Application application;
    Application application1;
    Application application2;

    @Mock
    Student student;

    @Mock
    Vacancy vacancy;


    @BeforeEach
    public void setUp() {
        application = new Application();
        application.setApplicationID(1L);
        application.setStudent(student);
        application.setVacancy(vacancy);

        application1 = new Application();
        application1.setApplicationID(2L);
        application1.setStudent(student);
        application1.setVacancy(vacancy);

        application2 = new Application();
        application2.setApplicationID(2L);
        application2.setStudent(student);
        application2.setVacancy(vacancy);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, application.getApplicationID());
        assertEquals(student, application.getStudent());
        assertEquals(vacancy, application.getVacancy());


    }
    @Test
    public void testEquals() {
        assertTrue(application.equals(application));
        assertTrue(application1.equals(application1));
        assertTrue(application1.equals(application2));
        assertFalse(application.equals(application1));

    }
}
