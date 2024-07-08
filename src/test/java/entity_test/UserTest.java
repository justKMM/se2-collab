package entity_test;

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

    private static final String TESTPW = "password";
    private static final int USER_ID_1 = 1;
    private static final int USER_ID_2 = 2;
    private static final String USERNAME_TESTUSER = "testuser";
    private static final String USERNAME_TESTUSER1 = "testuser1";
    private static final String EMAIL_TESTUSER = "testuser@example.com";
    private static final String EMAIL_TESTUSER1 = "testuser1@example.com";
    private static final String EMAIL_DIFFERENT = "different@example.com";
    private static final String PASSWORD1 = "password1";
    private static final String PASSWORD2 = "password2";
    private static final int BLACKLISTED_FALSE = 0;
    private static final int BLACKLISTED_TRUE = 1;

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
        user.setUserID(USER_ID_1);
        user.setUsername(USERNAME_TESTUSER);
        user.setPassword(TESTPW);
        user.setBlacklisted(BLACKLISTED_FALSE);
        user.setEmail(EMAIL_TESTUSER);
        user.setProfile(profile);

        user1 = new User();
        user1.setUserID(USER_ID_1);
        user1.setUsername(USERNAME_TESTUSER1);
        user1.setPassword(PASSWORD1);
        user1.setBlacklisted(BLACKLISTED_FALSE);
        user1.setEmail(EMAIL_TESTUSER1);
        user1.setProfile(profile);

        user2 = new User();
        user2.setUserID(USER_ID_1);
        user2.setUsername(USERNAME_TESTUSER1);
        user2.setPassword(PASSWORD1);
        user2.setBlacklisted(BLACKLISTED_FALSE);
        user2.setEmail(EMAIL_DIFFERENT);
        user2.setProfile(profile);

        user3 = new User();
        user3.setUserID(USER_ID_1);
        user3.setUsername(USERNAME_TESTUSER1);
        user3.setPassword(PASSWORD1);
        user3.setBlacklisted(BLACKLISTED_TRUE);
        user3.setEmail(EMAIL_DIFFERENT);
        user3.setProfile(profile);

        user4 = new User();
        user4.setUserID(USER_ID_2);
        user4.setUsername(USERNAME_TESTUSER1);
        user4.setPassword(PASSWORD1);
        user4.setBlacklisted(BLACKLISTED_TRUE);
        user4.setEmail(EMAIL_DIFFERENT);
        user4.setProfile(profile);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(USER_ID_1, user.getUserID());
        assertEquals(USERNAME_TESTUSER, user.getUsername());
        assertEquals(TESTPW, user.getPassword());
        assertEquals(BLACKLISTED_FALSE, user.getBlacklisted());
        assertEquals(EMAIL_TESTUSER, user.getEmail());
        assertEquals(profile, user.getProfile());

        user.setPassword(PASSWORD2);
        assertEquals(PASSWORD2, user.getPassword());
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