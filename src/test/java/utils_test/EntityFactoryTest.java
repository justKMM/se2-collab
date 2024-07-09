package utils_test;

import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.model.entity.traits.FirstName;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import hbrs.se2.collhbrs.util.EntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email@example.com";
    private static final String LAST_NAME = "Doe";
    private static final String FIRST_NAME = "John";
    private static final String BUSINESS_NAME = "TechCorp";
    private static final String EMPLOYMENT_TYPE = "Full-Time";
    private static final String JOB_TITLE = "Software Engineer";
    private static final String LOCATION = "New York";
    private static final String JOB_DESCRIPTION = "Job Description";
    private static final String REQUIREMENTS_NAME = "Bachelor's Degree";
    private static final String RESPONSIBILITIES_NAME = "Develop software";
    private static final String APPLICATION_COVER_LETTER = "base64Letter";

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
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);

        assertNotNull(user);
        assertEquals(profile, user.getProfile());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(0, user.getBlacklisted());
    }

    @Test
    void testCreateStudent() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Student student = entityFactory.createStudent(user, LAST_NAME);

        assertNotNull(student);
        assertEquals(user, student.getUser());
        assertEquals(LAST_NAME, student.getLastName());
    }

    @Test
    void testCreateFirstName() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Student student = entityFactory.createStudent(user, LAST_NAME);
        FirstName firstName = entityFactory.createFirstName(FIRST_NAME, student);

        assertNotNull(firstName);
        assertEquals(FIRST_NAME, firstName.getFirstNameName());
        assertEquals(student, firstName.getStudent());
    }

    @Test
    void testCreateBusiness() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Business business = entityFactory.createBusiness(BUSINESS_NAME, user);

        assertNotNull(business);
        assertEquals(BUSINESS_NAME, business.getName());
        assertEquals(user, business.getUser());
    }

    @Test
    void testCreateVacancy() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Business business = entityFactory.createBusiness(BUSINESS_NAME, user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy(EMPLOYMENT_TYPE, JOB_TITLE, LOCATION, JOB_DESCRIPTION, business, date);

        assertNotNull(vacancy);
        assertEquals(EMPLOYMENT_TYPE, vacancy.getEmploymentType());
        assertEquals(JOB_TITLE, vacancy.getTitle());
        assertEquals(LOCATION, vacancy.getLocation());
        assertEquals(JOB_DESCRIPTION, vacancy.getDescription());
        assertEquals(business, vacancy.getBusiness());
        assertEquals(date, vacancy.getPublishDate());
    }

    @Test
    void testCreateRequirements() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Business business = entityFactory.createBusiness(BUSINESS_NAME, user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy(EMPLOYMENT_TYPE, JOB_TITLE, LOCATION, JOB_DESCRIPTION, business, date);
        Requirements requirements = entityFactory.createRequirements(vacancy, REQUIREMENTS_NAME);

        assertNotNull(requirements);
        assertEquals(vacancy, requirements.getVacancy());
        assertEquals(REQUIREMENTS_NAME, requirements.getRequirementsName());
    }

    @Test
    void testCreateResponsibilities() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Business business = entityFactory.createBusiness(BUSINESS_NAME, user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy(EMPLOYMENT_TYPE, JOB_TITLE, LOCATION, JOB_DESCRIPTION, business, date);
        Responsibilities responsibilities = entityFactory.createResponsibilities(vacancy, RESPONSIBILITIES_NAME);

        assertNotNull(responsibilities);
        assertEquals(vacancy, responsibilities.getVacancy());
        assertEquals(RESPONSIBILITIES_NAME, responsibilities.getResponsibilitiesName());
    }

    @Test
    void testCreateApplication() {
        Profile profile = entityFactory.createProfile();
        User user = entityFactory.createUser(profile, USERNAME, PASSWORD, EMAIL);
        Student student = entityFactory.createStudent(user, LAST_NAME);
        Business business = entityFactory.createBusiness(BUSINESS_NAME, user);
        Date date = new Date(System.currentTimeMillis());
        Vacancy vacancy = entityFactory.createVacancy(EMPLOYMENT_TYPE, JOB_TITLE, LOCATION, JOB_DESCRIPTION, business, date);
        Application application = entityFactory.createApplication(vacancy, student, APPLICATION_COVER_LETTER);

        assertNotNull(application);
        assertEquals(vacancy, application.getVacancy());
        assertEquals(student, application.getStudent());
        assertEquals(APPLICATION_COVER_LETTER, application.getCoverLetter());
    }
}