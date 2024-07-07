package dtoTest;

import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationDTOTest {

    private ApplicationDTO applicationDTO;

    @BeforeEach
    void setUp() {
        Student student = new Student();
        student.setStudentID(1L);
        student.setLastName("Tester");

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(1L);
        vacancy.setTitle("Software Engineer");

        Application application = new Application();
        application.setApplicationID(1L);
        application.setStudent(student);
        application.setVacancy(vacancy);

        this.applicationDTO = new ApplicationDTO(application);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, applicationDTO.getApplicationID());
        assertEquals("Tester", applicationDTO.getStudent().getLastName());
        assertEquals("Software Engineer", applicationDTO.getVacancy().getTitle());

        applicationDTO.setApplicationID(2L);
        applicationDTO.getStudent().setLastName("Test");
        applicationDTO.getVacancy().setTitle("Data Tester");

        assertEquals(2L, applicationDTO.getApplicationID());
        assertEquals("Test", applicationDTO.getStudent().getLastName());
        assertEquals("Data Tester", applicationDTO.getVacancy().getTitle());
    }
}

