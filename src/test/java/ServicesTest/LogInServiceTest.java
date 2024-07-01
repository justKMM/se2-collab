package ServicesTest;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LogInServiceTest {

    public static final String USERNAME = "sasha123";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginPositive() {
        User mockUser = new User();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword("passwort123");

        when(userRepository.findByUsernameAndPassword(USERNAME, "passwort123")).thenReturn(mockUser);

        User result = loginService.login(USERNAME, "passwort123");
        assertNotNull(result);
        assertEquals(USERNAME, result.getUsername());
    }

    @Test
    void testLoginNegative() {
        when(userRepository.findByUsernameAndPassword(USERNAME, "wrong123")).thenReturn(null);
        assertNull(loginService.login(USERNAME, "wrong123"));
        assertNull(loginService.login("wrongsasha", "wrong123"));
    }

    @Test
    void testIsBlacklistedPositive() {
        User mockUser = new User();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword("passwort123");
        mockUser.setBlacklisted(1);

        when(userRepository.findByUsernameAndPassword(USERNAME, "passwort123")).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(USERNAME, "passwort123"));
        userDTO.setUsername(USERNAME);
        userDTO.setPassword("passwort123");

        assertTrue(loginService.isBlacklisted(userDTO));
    }

    @Test
    void testIsBlacklistedNegative() {
        User mockUser = new User();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword("passwort123");
        mockUser.setBlacklisted(0);

        when(userRepository.findByUsernameAndPassword(USERNAME, "passwort123")).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(USERNAME, "passwort123"));
        userDTO.setUsername(USERNAME);
        userDTO.setPassword("passwort123");

        assertFalse(loginService.isBlacklisted(userDTO));
    }
}
