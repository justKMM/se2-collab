package EntityTest;

import hbrs.se2.collhbrs.entity.Profile;
import hbrs.se2.collhbrs.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    Profile profile;
    Profile profile1;

    @BeforeEach
    public void setUp() {
        profile = new Profile();
        profile.setProfileID(1L);
        profile.setAvatarUrl("ExampleURL");
        profile.setProfileDescription("ProfileDescription");
        profile.setLinkedinUsername("usernameLI");
        profile.setXingUsername("usernameXing");

        profile1 = new Profile();
        profile1.setProfileID(1L);
        profile1.setAvatarUrl("ExampleURL");
        profile1.setProfileDescription("ProfileDescription");
        profile1.setLinkedinUsername("usernameLI1");
        profile1.setXingUsername("usernameXing1");
    }
    @Test
    public void testSettersandGetters() {
        assertEquals(1L,profile.getProfileID());
        assertEquals("ExampleURL",profile.getAvatarUrl());
        assertEquals("ProfileDescription",profile.getProfileDescription());
        assertEquals("usernameLI",profile.getLinkedinUsername());
        assertEquals("usernameXing",profile.getXingUsername());
    }
    @Test
    public void testEquals() {
        assertTrue(profile.equals(profile));
        assertTrue(profile1.equals(profile1));
        assertFalse(profile.equals(profile1));
    }
}
