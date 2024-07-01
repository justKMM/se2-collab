package EntityTest;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ApplicationTest {

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
    void testGettersAndSetters() {
        assertEquals(1L, application.getApplicationID());
        assertEquals(student, application.getStudent());
        assertEquals(vacancy, application.getVacancy());


    }

    @Test
    void testEquals() {
        assertEquals(application, application);
        assertEquals(application1, application1);
        assertEquals(application1, application2);
        assertNotEquals(application, application1);

    }
}
