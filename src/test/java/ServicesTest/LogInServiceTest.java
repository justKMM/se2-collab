package ServicesTest;

import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.dto.imp.UserDTOImpl;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.service.db.exceptions.DatabaseLayerException;
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
    void testAuthenticatePositive() throws DatabaseLayerException {

        UserDTOImpl mockUser = new UserDTOImpl();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);

        when(userRepository.findUserByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        assertTrue(loginService.authenticate(USERNAME, PASSWORD));
        assertEquals(mockUser, loginService.getCurrentUser());
    }

    @Test
    void testAuthenticateNegative() throws DatabaseLayerException {


        when(userRepository.findUserByUsernameAndPassword(USERNAME, WRONGPASSWORD)).thenReturn(null);

        assertFalse(loginService.authenticate(USERNAME, WRONGPASSWORD));
        assertNull(loginService.getCurrentUser());
    }

    @Test
    void testIsBlacklistedPositive() throws DatabaseLayerException {

        UserDTOImpl mockUser = new UserDTOImpl();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);
        mockUser.setBlacklisted(1);

        when(userRepository.findUserByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        assertTrue(loginService.isBlacklisted(USERNAME, PASSWORD));
    }

    @Test
    void testIsBlacklistedFNegative() throws DatabaseLayerException {

        UserDTOImpl mockUser = new UserDTOImpl();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);
        mockUser.setBlacklisted(0);

        when(userRepository.findUserByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        assertFalse(loginService.isBlacklisted(USERNAME, PASSWORD));
    }

    @Test
    void testIsBlacklistedUserNotFound() throws DatabaseLayerException {

        when(userRepository.findUserByUsernameAndPassword(USERNAME, WRONGPASSWORD)).thenReturn(null);

        assertTrue(loginService.isBlacklisted(USERNAME, WRONGPASSWORD));
    }

    @Test
    void testGetCurrentUser() throws DatabaseLayerException {

        UserDTOImpl mockUser = new UserDTOImpl();
        mockUser.setUsername(USERNAME);
        mockUser.setPassword(PASSWORD);

        when(userRepository.findUserByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(mockUser);

        loginService.authenticate(USERNAME, PASSWORD);

        UserDTO currentUser = loginService.getCurrentUser();

        assertNotNull(currentUser);
        assertEquals(mockUser, currentUser);
    }

    @Test
    void testGetUser() {

        when(userRepository.findUserByUsernameAndPassword(USERNAME, PASSWORD)).thenThrow(new org.springframework.dao.DataAccessResourceFailureException("Database error"));

        assertThrows(DatabaseLayerException.class, () -> loginService.authenticate(USERNAME, PASSWORD));
    }
}
