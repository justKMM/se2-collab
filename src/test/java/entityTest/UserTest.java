package entityTest;

import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private final static String TESTPW = "password";
    User user;
    User user1;
    User user2;
    User user3;
    User user4;

    @Mock
    Profile profile;
    Profile profile1;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserID(1);
        user.setUsername("testuser");
        user.setPassword(TESTPW);
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

        user3 = new User();
        user3.setUserID(1);
        user3.setUsername("testuser1");
        user3.setPassword("password1");
        user3.setBlacklisted(1);
        user3.setEmail("different@example.com");
        user3.setProfile(profile);

        user4 = new User();
        user4.setUserID(2);
        user4.setUsername("testuser1");
        user4.setPassword("password1");
        user4.setBlacklisted(1);
        user4.setEmail("different@example.com");
        user4.setProfile(profile);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, user.getUserID());
        assertEquals("testuser", user.getUsername());
        assertEquals(TESTPW, user.getPassword());
        assertEquals(0, user.getBlacklisted());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals(profile, user.getProfile());

        user.setPassword("password2");
        assertEquals("password2", user.getPassword());
        assertNotEquals(TESTPW, user.getPassword());
    }

    @Test
    void testEquals() {
        assertEquals(user1, user2);
        assertEquals(user4, user4);
        assertEquals(user3, user3);
        assertEquals(user2, user2);
        assertEquals(user1, user1);
        assertEquals(user, user);
        assertNotEquals(null, user);
        assertNotEquals("lol", user);
        assertNotEquals(user, user1);
        assertNotEquals(user, user4);
        assertNotEquals(user, user2);
        assertNotEquals(user2, user3);
    }

    @Test
    void testHashCode() {
        assertNotEquals(user.hashCode(), user1.hashCode());
        assertEquals(user2.hashCode(), user2.hashCode());
    }
}
