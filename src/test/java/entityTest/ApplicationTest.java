package entityTest;

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

        application3 = new Application();
        application3.setApplicationID(3L);
        application3.setStudent(student1);
        application3.setVacancy(vacancy);

        application4 = new Application();
        application4.setApplicationID(4L);
        application4.setStudent(student);
        application4.setVacancy(vacancy1);
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
        assertEquals(application2, application2);
        assertEquals(application3, application3);
        assertEquals(application4, application4);
        assertEquals(application1, application2);
        assertNotEquals(application, application1);
        assertNotEquals(application3, application4);
        assertNotEquals(null, application);
        assertNotEquals("lol", application);

    }
}
