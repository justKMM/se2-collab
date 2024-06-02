package DTOsTest;

import hbrs.se2.collhbrs.model.dto.ProfileDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDTOTest {

    private UserDTO userDTO;

    @Mock
    ProfileDTO profileDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(new User());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, userDTO.getUserID());
        assertNull( userDTO.getUsername());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getPassword());
        assertEquals(0, userDTO.getBlacklisted());
        assertNull(userDTO.getProfile());

        userDTO.setUserID(1L);
        userDTO.setUsername("testname");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");
        userDTO.setProfile(new Profile());
        userDTO.setBlacklisted(1);

        assertEquals(1L, userDTO.getUserID());
        assertEquals("testname", userDTO.getUsername());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals("password", userDTO.getPassword());
        assertEquals(1, userDTO.getBlacklisted());

    }
}
