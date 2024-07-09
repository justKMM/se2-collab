package service_test;

import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.service.SecurityService;
import hbrs.se2.collhbrs.util.Globals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SecurityServiceTest {

    private static final String STUDENT_USERNAME = "student";
    private static final String BUSINESS_USERNAME = "business";
    private static final String PASSWORD = "password";
    private static final Long USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private BusinessRepository businessRepository;

    @InjectMocks
    private SecurityService securityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsernameUserFoundStudentRole() {
        User user = new User();
        user.setUserID(USER_ID);
        user.setUsername(STUDENT_USERNAME);
        user.setPassword(PASSWORD);

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(studentRepository.existsByUserUserID(anyLong())).thenReturn(true);
        when(businessRepository.existsByUserUserID(anyLong())).thenReturn(false);

        UserDetails userDetails = securityService.loadUserByUsername(STUDENT_USERNAME);

        assertEquals(STUDENT_USERNAME, userDetails.getUsername());
        assertEquals(PASSWORD, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + Globals.Roles.STUDENT)));
    }

    @Test
    void testLoadUserByUsernameUserFoundBusinessRole() {
        User user = new User();
        user.setUserID(USER_ID);
        user.setUsername(BUSINESS_USERNAME);
        user.setPassword(PASSWORD);

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(studentRepository.existsByUserUserID(anyLong())).thenReturn(false);
        when(businessRepository.existsByUserUserID(anyLong())).thenReturn(true);

        UserDetails userDetails = securityService.loadUserByUsername(BUSINESS_USERNAME);

        assertEquals(BUSINESS_USERNAME, userDetails.getUsername());
        assertEquals(PASSWORD, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + Globals.Roles.BUSINESS)));
    }
}