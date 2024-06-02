package hbrs.se2.collhbrs;

import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.ProfileRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RoundTripTest {

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
        long UserID = user.getUserID();


        // Schritt 2: Read (R)
        Optional<User> wrapper = userRepository.findById(UserID);
        User userAfterCreate = null;
        if (wrapper.isPresent()) {
            userAfterCreate = wrapper.get();
        }

        // Überprüfung auf Gleichheit
        assertEquals(userAfterCreate.getUsername(), "Jakob9");
        assertEquals(userAfterCreate.getPassword(), "Jakob1234!");
        assertEquals(userAfterCreate.getBlacklisted(), 0);
        assertEquals(userAfterCreate.getEmail(), "jakobus@test.de");
        
        // Schritt 3: Update (U)
        userAfterCreate.setUsername("Jakob17");
        userAfterCreate.setPassword("jakoB1734!");
        userRepository.save(userAfterCreate);
        // Überprüfung auf Gleichheit
        assertEquals(userAfterCreate.getUsername(), "Jakob17");
        assertEquals(userAfterCreate.getPassword(), "jakoB1734!");

        // Schritt 4: Deletion (D)
        userRepository.deleteById(UserID);
        // Wurde User wirklich gelöscht?
        Optional<User> wrapperAfterDelete = userRepository.findById(UserID);
        assertFalse(wrapperAfterDelete.isPresent());
    }
    
}
