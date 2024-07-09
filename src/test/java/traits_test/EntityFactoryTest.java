package hbrs.se2.collhbrs.util;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {

    private EntityFactory entityFactory;

    @BeforeEach
    public void setUp() {
        entityFactory = new EntityFactory();
    }

    @Test
    void testCreateProfile() {
        Profile profile = entityFactory.createProfile();
        assertNotNull(profile);
    }

    @Test
    void testCreateUser() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");

        assertNotNull(user);
        assertEquals(profile, user.getProfile());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email@example.com", user.getEmail());
        assertEquals(0, user.getBlacklisted());
    }

    @Test
    void testCreateStudent() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Student student = entityFactory.createStudent(user, "Doe");

        assertNotNull(student);
        assertEquals(user, student.getUser());
        assertEquals("Doe", student.getLastName());
    }

    @Test
    void testCreateFirstName() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Student student = entityFactory.createStudent(user, "Doe");
        FirstName firstName = entityFactory.createFirstName("John", student);

        assertNotNull(firstName);
        assertEquals("John", firstName.getFirstNameName());
        assertEquals(student, firstName.getStudent());
    }

    @Test
    void testCreateBusiness() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Business business = entityFactory.createBusiness("TechCorp", user);

        assertNotNull(business);
        assertEquals("TechCorp", business.getName());
        assertEquals(user, business.getUser());
    }

    @Test
    void testCreateVacancy() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Business business = entityFactory.createBusiness("TechCorp", user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy("Full-Time", "Software Engineer", "New York", "Job Description", business, date);

        assertNotNull(vacancy);
        assertEquals("Full-Time", vacancy.getEmploymentType());
        assertEquals("Software Engineer", vacancy.getTitle());
        assertEquals("New York", vacancy.getLocation());
        assertEquals("Job Description", vacancy.getDescription());
        assertEquals(business, vacancy.getBusiness());
        assertEquals(date, vacancy.getPublishDate());
    }

    @Test
    void testCreateRequirements() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Business business = entityFactory.createBusiness("TechCorp", user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy("Full-Time", "Software Engineer", "New York", "Job Description", business, date);
        Requirements requirements = entityFactory.createRequirements(vacancy, "Bachelor's Degree");

        assertNotNull(requirements);
        assertEquals(vacancy, requirements.getVacancy());
        assertEquals("Bachelor's Degree", requirements.getRequirementsName());
    }

    @Test
    void testCreateResponsibilities() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Business business = entityFactory.createBusiness("TechCorp", user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy("Full-Time", "Software Engineer", "New York", "Job Description", business, date);
        Responsibilities responsibilities = entityFactory.createResponsibilities(vacancy, "Develop software");

        assertNotNull(responsibilities);
        assertEquals(vacancy, responsibilities.getVacancy());
        assertEquals("Develop software", responsibilities.getResponsibilitiesName());
    }

    @Test
    void testCreateApplication() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, "username", "password", "email@example.com");
        Student student = entityFactory.createStudent(user, "Doe");
        Business business = entityFactory.createBusiness("TechCorp", user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy("Full-Time", "Software Engineer", "New York", "Job Description", business, date);
        Application application = entityFactory.createApplication(vacancy, student, "base64Letter");

        assertNotNull(application);
        assertEquals(vacancy, application.getVacancy());
        assertEquals(student, application.getStudent());
        assertEquals("base64Letter", application.getCoverLetter());
    }
}