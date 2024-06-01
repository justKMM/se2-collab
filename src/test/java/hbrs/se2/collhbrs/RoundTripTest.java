package hbrs.se2.collhbrs;

import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoundTripTest {
  
  @Autowired
  private UserRepository userRepository;
  
  @Test
  void createReadAndDeleteAUser() {
    
    // Durchführung CRUD-Test, Schritt 1: Create (C)
    User user = new User();
    user.setUsername( "Jakob9" );
    user.setPassword("Jakob1234!");
    user.setBlacklisted(0);
    user.setEmail("jakobus@test.de");
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
    assertEquals( userAfterCreate.getUsername() , "Jakob9" );
    assertEquals( userAfterCreate.getPassword() , "Jakob1234" );
    assertEquals( userAfterCreate.getBlacklisted() , 0 );
    assertEquals( userAfterCreate.getEmail() , "jakobus@test.de" );
    // Identität prüfen
    //assertNotSame( user , userAfterCreate ); !!!
    
    // Schritt 4: Deletion (D)
    userRepository.deleteById(UserID);
    // Wurde User wirklich gelöscht?
    Optional<User> wrapperAfterDelete = userRepository.findById(UserID);
    System.out.println("Wrapper: " + wrapperAfterDelete);
    assertFalse( wrapperAfterDelete.isPresent() );
  }
  
  @AfterEach
  public void deleteUser(){
    // Hier könnte man nach einem RoundTrip die DB noch weiter bereinigen
  }
  
}
