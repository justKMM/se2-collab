package service_test;

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

    private static final String RIGHT_U = "sasha123";
    private static final String RIGHT_P = "passwort123";
    private static final String WRONG_P = "wrong123";
    private static final String WRONG_U = "wrongsasha";

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
        mockUser.setUsername(RIGHT_U);
        mockUser.setPassword(RIGHT_P);

        when(userRepository.findByUsernameAndPassword(RIGHT_U, RIGHT_P)).thenReturn(mockUser);

        User result = loginService.login(RIGHT_U, RIGHT_P);
        assertNotNull(result);
        assertEquals(RIGHT_U, result.getUsername());
    }

    @Test
    void testLoginNegative() {
        when(userRepository.findByUsernameAndPassword(RIGHT_U, WRONG_P)).thenReturn(null);
        assertNull(loginService.login(RIGHT_U, WRONG_P));
        assertNull(loginService.login(WRONG_U, WRONG_P));
    }

    @Test
    void testIsBlacklistedPositive() {
        User mockUser = new User();
        mockUser.setUsername(RIGHT_U);
        mockUser.setPassword(RIGHT_P);
        mockUser.setBlacklisted(1);

        when(userRepository.findByUsernameAndPassword(RIGHT_U, RIGHT_P)).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(RIGHT_U, RIGHT_P));
        userDTO.setUsername(RIGHT_U);
        userDTO.setPassword(RIGHT_P);

        assertTrue(loginService.isBlacklisted(userDTO));
    }

    @Test
    void testIsBlacklistedNegative() {
        User mockUser = new User();
        mockUser.setUsername(RIGHT_U);
        mockUser.setPassword(RIGHT_P);
        mockUser.setBlacklisted(0);

        when(userRepository.findByUsernameAndPassword(RIGHT_U, RIGHT_P)).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(RIGHT_U, RIGHT_P));
        userDTO.setUsername(RIGHT_U);
        userDTO.setPassword(RIGHT_P);

        assertFalse(loginService.isBlacklisted(userDTO));
    }
}