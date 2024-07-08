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

    private static final String u = "sasha123";
    private static final String p = "passwort123";
    private static final String wrong_p = "wrong123";
    private static final String wrong_u = "wrongsasha";

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
        mockUser.setUsername(u);
        mockUser.setPassword(p);

        when(userRepository.findByUsernameAndPassword(u, p)).thenReturn(mockUser);

        User result = loginService.login(u, p);
        assertNotNull(result);
        assertEquals(u, result.getUsername());
    }

    @Test
    void testLoginNegative() {
        when(userRepository.findByUsernameAndPassword(u, wrong_p)).thenReturn(null);
        assertNull(loginService.login(u, wrong_p));
        assertNull(loginService.login(wrong_u, wrong_p));
    }

    @Test
    void testIsBlacklistedPositive() {
        User mockUser = new User();
        mockUser.setUsername(u);
        mockUser.setPassword(p);
        mockUser.setBlacklisted(1);

        when(userRepository.findByUsernameAndPassword(u, p)).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(u, p));
        userDTO.setUsername(u);
        userDTO.setPassword(p);

        assertTrue(loginService.isBlacklisted(userDTO));
    }

    @Test
    void testIsBlacklistedNegative() {
        User mockUser = new User();
        mockUser.setUsername(u);
        mockUser.setPassword(p);
        mockUser.setBlacklisted(0);

        when(userRepository.findByUsernameAndPassword(u, p)).thenReturn(mockUser);

        UserDTO userDTO = new UserDTO(loginService.login(u, p));
        userDTO.setUsername(u);
        userDTO.setPassword(p);

        assertFalse(loginService.isBlacklisted(userDTO));
    }
}