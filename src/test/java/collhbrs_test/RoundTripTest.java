package collhbrs_test;

import hbrs.se2.collhbrs.CollhbrsApplication;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.ProfileRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = CollhbrsApplication.class)
class RoundTripTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    void createReadAndDeleteAUser() {

        // Durchführung CRUD-Test, Schritt 1: Create (C)
        User user = new User();
        user.setUsername("Jakob9");
        user.setPassword("Jakob1234!");
        user.setBlacklisted(0);
        user.setEmail("jakobus@test.de");
        Profile profile = new Profile();
        profileRepository.save(profile);
        user.setProfile(profile);
        // Speichern in der Datenbank
        userRepository.save(user);

        // Generierte User-ID holen
        long userID = user.getUserID();


        // Schritt 2: Read (R)
        Optional<User> wrapper = userRepository.findById(userID);
        User userAfterCreate = null;
        if (wrapper.isPresent()) {
            userAfterCreate = wrapper.get();
        }

        // Überprüfung auf Gleichheit

        assert userAfterCreate != null;
        assertEquals("Jakob9", userAfterCreate.getUsername());
        assertEquals("Jakob1234!", userAfterCreate.getPassword());
        assertEquals(0, userAfterCreate.getBlacklisted());
        assertEquals("jakobus@test.de", userAfterCreate.getEmail());

        // Schritt 3: Update (U)
        userAfterCreate.setUsername("Jakob17");
        userAfterCreate.setPassword("jakoB1734!");
        userRepository.save(userAfterCreate);
        // Überprüfung auf Gleichheit
        assertEquals("Jakob17", userAfterCreate.getUsername());
        assertEquals("jakoB1734!", userAfterCreate.getPassword());

        // Schritt 4: Deletion (D)
        userRepository.deleteById(userID);
        // Wurde User wirklich gelöscht?
        Optional<User> wrapperAfterDelete = userRepository.findById(userID);
        assertFalse(wrapperAfterDelete.isPresent());
    }

}
