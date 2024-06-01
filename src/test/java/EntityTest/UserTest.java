package EntityTest;

import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    User user;
    User user1;
    User user2;

    @Mock
    Profile profile;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserID(1);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setBlacklisted(0);
        user.setEmail("testuser@example.com");
        user.setProfile(profile);

        user1 = new User();
        user1.setUserID(1);
        user1.setUsername("testuser1");
        user1.setPassword("password1");
        user1.setBlacklisted(0);
        user1.setEmail("testuser1@example.com");
        user1.setProfile(profile);

        user2 = new User();
        user2.setUserID(1);
        user2.setUsername("testuser1");
        user2.setPassword("password1");
        user2.setBlacklisted(0);
        user2.setEmail("different@example.com");
        user2.setProfile(profile);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, user.getUserID());
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(0, user.getBlacklisted());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals(profile, user.getProfile());

        user.setPassword("password2");
        assertEquals("password2", user.getPassword());
        assertNotEquals("password", user.getPassword());
    }
    @Test
    public void testEquals() {
        assertTrue(user1.equals(user2));
        assertTrue(user1.equals(user1));
        assertTrue(user.equals(user));
        //Email scheint nicht gleich sein zu müssen, damit 2 user als Equals gelten!?
        assertFalse(user.equals(user1));
    }
    @Test
    public void testHashCode() {
        assertNotEquals(user.hashCode(),user1.hashCode());
        assertEquals(user2.hashCode(),user2.hashCode());
    }
}
