package DTOsTest;

import hbrs.se2.collhbrs.model.dto.ProfileDTO;
import hbrs.se2.collhbrs.model.entity.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProfileDTOTest {

    private ProfileDTO profileDTO;

    @BeforeEach
    void setUp() {
        profileDTO = new ProfileDTO(new Profile());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, profileDTO.getProfileID());
        assertNull(profileDTO.getAvatarUrl());
        assertNull(profileDTO.getProfileDescription());
        assertNull(profileDTO.getXingUsername());
        assertNull(profileDTO.getLinkedinUsername());

        profileDTO.setProfileID(1L);
        profileDTO.setAvatarUrl("https://example.com/avatar.jpg");
        profileDTO.setProfileDescription("A software engineer with a passion for testing.");
        profileDTO.setXingUsername("xingUser123");
        profileDTO.setLinkedinUsername("linkedinUser123");

        assertEquals(1L, profileDTO.getProfileID());
        assertEquals("https://example.com/avatar.jpg", profileDTO.getAvatarUrl());
        assertEquals("A software engineer with a passion for testing.", profileDTO.getProfileDescription());
        assertEquals("xingUser123", profileDTO.getXingUsername());
        assertEquals("linkedinUser123", profileDTO.getLinkedinUsername());
    }
}

