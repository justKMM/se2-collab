package DTOsTest;

import hbrs.se2.collhbrs.model.dto.imp.UserDTOImpl;
import hbrs.se2.collhbrs.model.dto.imp.ProfileDTOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDTOImplTest {

    private UserDTOImpl userDTO;

    @Mock
    ProfileDTOImpl profileDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTOImpl();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, userDTO.getUserID());
        assertNull( userDTO.getUsername());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getPassword());
        //assertNull(userDTO.getEntity());
        assertEquals(0, userDTO.getBlacklisted());
        assertNull(userDTO.getProfile());

        userDTO.setUserID(1L);
        userDTO.setUsername("testname");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");
        userDTO.setProfile(profileDTO);
        userDTO.setBlacklisted(1);

        assertEquals(1L, userDTO.getUserID());
        assertEquals("testname", userDTO.getUsername());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals("password", userDTO.getPassword());
        assertEquals(profileDTO, userDTO.getProfile());
        assertEquals(1, userDTO.getBlacklisted());

    }
}
