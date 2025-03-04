package entity_test;

import hbrs.se2.collhbrs.model.entity.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProfileTest {

    private static final String EXAMPLE_URL = "ExampleURL";
    private static final String PROFILEDESCRIPTION = "ProfileDescription";

    Profile profile;
    Profile profile1;

    @BeforeEach
    public void setUp() {
        profile = new Profile();
        profile.setProfileID(1L);
        profile.setAvatar(EXAMPLE_URL);
        profile.setProfileDescription(PROFILEDESCRIPTION);
        profile.setLinkedinUsername("usernameLI");
        profile.setXingUsername("usernameXing");

        profile1 = new Profile();
        profile1.setProfileID(1L);
        profile1.setAvatar(EXAMPLE_URL);
        profile1.setProfileDescription(PROFILEDESCRIPTION);
        profile1.setLinkedinUsername("usernameLI1");
        profile1.setXingUsername("usernameXing1");
    }

    @Test
    void testSettersandGetters() {
        assertEquals(1L, profile.getProfileID());
        assertEquals(EXAMPLE_URL, profile.getAvatar());
        assertEquals(PROFILEDESCRIPTION, profile.getProfileDescription());
        assertEquals("usernameLI", profile.getLinkedinUsername());
        assertEquals("usernameXing", profile.getXingUsername());
    }

    @Test
    void testEquals() {
        assertEquals(profile, profile);
        assertEquals(profile1, profile1);
        assertNotEquals(profile, profile1);
    }
}
