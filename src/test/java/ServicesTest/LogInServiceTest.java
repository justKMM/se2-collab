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
import static org.mockito.Mockito.*;

class LogInServiceTest {

    public static final String USERNAME = "sasha123";
    public static final String WRONGUSERNAME = "wrongsasha";
    public static final String PASSWORD = "passwort123";
    public static final String WRONGPASSWORD = "wrong123";

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
        mockUser.setPassword(PASSWORD);

        when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        User result = loginService.login(USERNAME, PASSWORD);
        assertNotNull(result);
        assertEquals(USERNAME, result.getUsername());
    }

    @Test
    void testLoginNegative() {
        when(userRepository.findByUsernameAndPassword(USERNAME, WRONGPASSWORD)).thenReturn(null);
        assertNull(loginService.login(USERNAME, WRONGPASSWORD));
        assertNull(loginService.login(WRONGUSERNAME, WRONGPASSWORD));
    }

    @Test
    void testIsBlacklistedPositive() {
        User mockUser = new User();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);
        mockUser.setBlacklisted(1);

        when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(USERNAME, PASSWORD));
        userDTO.setUsername(USERNAME);
        userDTO.setPassword(PASSWORD);

        assertTrue(loginService.isBlacklisted(userDTO));
    }

    @Test
    void testIsBlacklistedNegative() {
        User mockUser = new User();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);
        mockUser.setBlacklisted(0);

        when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(USERNAME, PASSWORD));
        userDTO.setUsername(USERNAME);
        userDTO.setPassword(PASSWORD);

        assertFalse(loginService.isBlacklisted(userDTO));
    }
}
