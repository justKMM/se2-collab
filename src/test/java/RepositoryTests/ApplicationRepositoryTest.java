package RepositoryTests;

import hbrs.se2.collhbrs.CollhbrsApplication;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.repository.ApplicationRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = CollhbrsApplication.class)
public class ApplicationRepositoryTest {

/*    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void testSaveAndFindById() {
        Application application = new Application();

        Application savedApplication = applicationRepository.save(application);

        Optional<Application> retrievedApplicationOptional = applicationRepository.findById(savedApplication.getApplicationID());

        assertTrue(retrievedApplicationOptional.isPresent());
        Application retrievedApplication = retrievedApplicationOptional.get();

        assertEquals(savedApplication, retrievedApplication);
    }

    @Test
    public void testFindAll() {
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
    public void testDelete() {
        Application application = new Application();

        Application savedApplication = applicationRepository.save(application);

        applicationRepository.delete(savedApplication);

        Optional<Application> deletedApplicationOptional = applicationRepository.findById(savedApplication.getApplicationID());
        assertFalse(deletedApplicationOptional.isPresent());
    }*/
}
