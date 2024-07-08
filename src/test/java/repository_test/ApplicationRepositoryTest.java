package repository_test;

import hbrs.se2.collhbrs.CollhbrsApplication;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.repository.ApplicationRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CollhbrsApplication.class)
class ApplicationRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void testSaveAndFindById() {
        Application application = new Application();

        Application savedApplication = applicationRepository.save(application);

        Optional<Application> retrievedApplicationOptional = applicationRepository.findById(savedApplication.getApplicationID());

        assertTrue(retrievedApplicationOptional.isPresent());
        Application retrievedApplication = retrievedApplicationOptional.get();

        assertEquals(savedApplication, retrievedApplication);
    }

    @Test
    void testFindAll() {
        Application app1 = new Application();
        Application app2 = new Application();

        entityManager.persist(app1);
        entityManager.persist(app2);
        entityManager.flush();

        List<Application> allApplications = applicationRepository.findAll();

        assertEquals(2, allApplications.size());
        assertTrue(allApplications.contains(app1));
        assertTrue(allApplications.contains(app2));
    }

    @Test
    void testDelete() {
        Application application = new Application();

        Application savedApplication = applicationRepository.save(application);

        applicationRepository.delete(savedApplication);

        Optional<Application> deletedApplicationOptional = applicationRepository.findById(savedApplication.getApplicationID());
        assertFalse(deletedApplicationOptional.isPresent());
    }
}