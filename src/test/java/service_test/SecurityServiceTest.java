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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SecurityServiceTest {

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
    void testLoadUserByUsername_UserFound_StudentRole() {
        User user = new User();
        user.setUserID(1L);
        user.setUsername("student");
        user.setPassword("password");

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(studentRepository.existsByUserUserID(anyLong())).thenReturn(true);
        when(businessRepository.existsByUserUserID(anyLong())).thenReturn(false);

        UserDetails userDetails = securityService.loadUserByUsername("student");

        assertEquals("student", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + Globals.Roles.STUDENT)));
    }

    @Test
    void testLoadUserByUsername_UserFound_BusinessRole() {
        User user = new User();
        user.setUserID(1L);
        user.setUsername("business");
        user.setPassword("password");

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(studentRepository.existsByUserUserID(anyLong())).thenReturn(false);
        when(businessRepository.existsByUserUserID(anyLong())).thenReturn(true);

        UserDetails userDetails = securityService.loadUserByUsername("business");

        assertEquals("business", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + Globals.Roles.BUSINESS)));
    }
}