package entity_test;

import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ApplicationTest {

    private static final long APPLICATION_ID_1 = 1L;
    private static final long APPLICATION_ID_2 = 2L;
    private static final long APPLICATION_ID_3 = 3L;
    private static final long APPLICATION_ID_4 = 4L;

    Application application;
    Application application1;
    Application application2;
    Application application3;
    Application application4;

    @Mock
    Student student;
    Student student1;

    @Mock
    Vacancy vacancy;
    Vacancy vacancy1;

    @BeforeEach
    public void setUp() {
        application = new Application();
        application.setApplicationID(APPLICATION_ID_1);
        application.setStudent(student);
        application.setVacancy(vacancy);

        application1 = new Application();
        application1.setApplicationID(APPLICATION_ID_2);
        application1.setStudent(student);
        application1.setVacancy(vacancy);

        application2 = new Application();
        application2.setApplicationID(APPLICATION_ID_2);
        application2.setStudent(student);
        application2.setVacancy(vacancy);

        application3 = new Application();
        application3.setApplicationID(APPLICATION_ID_3);
        application3.setStudent(student1);
        application3.setVacancy(vacancy);

        application4 = new Application();
        application4.setApplicationID(APPLICATION_ID_4);
        application4.setStudent(student);
        application4.setVacancy(vacancy1);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(APPLICATION_ID_1, application.getApplicationID());
        assertEquals(student, application.getStudent());
        assertEquals(vacancy, application.getVacancy());
    }

    @Test
    void testEquals() {
        assertEquals(application, application);
        assertEquals(application1, application1);
        assertEquals(application2, application2);
        assertEquals(application3, application3);
        assertEquals(application4, application4);
        assertEquals(application1, application2);
        assertNotEquals(application, application1);
        assertNotEquals(application3, application4);
        assertNotEquals(null, application);
    }
}